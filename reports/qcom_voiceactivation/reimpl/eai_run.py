"""NumPy interpreter for EAI2 graphs (libeai_floating.so 2.2, float models).

Ground truth
------------
The reference implementation was recovered from ``native/libs/libeai_floating.so``
(symbols from ``.gnu_debugdata``, decompiled with Ghidra into
``evidence/ghidra/*.c``) and then *executed*: ``evidence/harness/eai_host.c``
maps the Android DSO, applies its relocations itself and drives the recovered
API (``eai_init_ex``/``eai_preapply``/``eai_set_property(0)``/``eai_apply``/
``eai_execute``), dumping every tensor.  Those dumps in ``evidence/native_dumps``
are the oracle this module is checked against (``verify_eai.py``).

Op status
---------
VERIFIED_OPS are compared numerically against the native engine tensor by tensor
on ``speech_float_speech_0.eai`` (max abs difference ~3e-08, float32 noise).
The other ops are transcribed best-effort from the decompiled forward functions
but are **not** bit-verified; running a graph that uses them requires
``allow_unverified=True`` and must not be presented as verified.

Graph execution follows ``network_forward_from``: layers run in file order and a
layer whose runtime flag bit 0x200 is set is skipped.
"""

from __future__ import annotations

import numpy as np

from eai_loader import EaiModel, load as load_model

VERIFIED_OPS = {"no_op", "relu", "gemm", "softmax", "lstm"}

UNVERIFIED_OPS = {
    # elementwise / unary - direct transcription, no oracle run yet
    "sigmoid", "tanh", "log", "exp", "sqrt", "clip", "quantize",
    "squeeze", "unsqueeze", "reshape", "transpose", "splat",
    "add", "sub", "mul", "div", "concat", "matmul",
    "softmax", "logsoftmax", "argmax", "reducemean", "reduce",
    "framer", "gru", "gather", "conv", "batchnorm", "globalavgpool",
    "maxpool", "averagepool", "convtranspose", "superconv", "supergemm",
    "superadd", "supersub", "supermul", "superdeconv", "superavgpool",
    "supermaxpool", "flowcontrol", "prelu", "maxout", "max", "argmin",
    "pow", "pad", "slice", "shape", "conv1d",
}


class UnsupportedOp(RuntimeError):
    def __init__(self, op, index, detail=""):
        super().__init__("op %r (layer %d)%s"
                         % (op, index, (": " + detail) if detail else ""))


def _sig(z):
    return 1.0 / (1.0 + np.exp(-z))


class EaiRunner:
    """Executes one EAI2 graph."""

    def __init__(self, model: EaiModel, allow_unverified: bool = False):
        self.m = model
        self.allow_unverified = allow_unverified
        self.consts = {}
        self.buf = {}
        for t in model.tensors:
            if t.group == 0:
                self.consts[t.id] = model.constant_bytes(t)
            else:
                self.buf[t.id] = np.zeros(t.shape if t.shape else (1,), np.float32)
        self.skipped = []
        self.trace = {}

    # -- tensors ---------------------------------------------------------
    def get(self, tid: int):
        if tid is None or tid < 0:
            return None
        if tid in self.consts:
            return self.consts[tid]
        return self.buf[tid]

    def put(self, tid: int, arr):
        t = self.m.tensors[tid]
        arr = np.asarray(arr, np.float32)
        if t.shape and arr.size == int(np.prod(t.shape)):
            arr = arr.reshape(t.shape)
        self.buf[tid] = arr

    # -- ops (verified) --------------------------------------------------
    def op_no_op(self, layer):
        """no_op_forward: identity copy, output takes the input values."""
        self.put(layer.out_ids[0], self.inp(layer, 0))

    def op_relu(self, layer):
        self.put(layer.out_ids[0], np.maximum(self.inp(layer, 0), 0.0))

    def op_gemm(self, layer):
        """gemm_forward flat path (evidence/ghidra/eai_tensor_ops.c:1331).

        The tensor carrying descriptor flag bit 0 is the constant weight W, the
        other matrix input is the activation X; the shapes come from the tensor
        descriptors, not from the layer record:
            M = W.dims[-2] (1 if rank < 2), K = W.dims[-1],
            N = X.dims[-2] (1 if rank < 2)
            out[N, M] = X[N, K] @ W[M, K].T + bias[M]
        """
        in0, in1 = self.inp(layer, 0), self.inp(layer, 1)
        t0 = self.m.tensors[layer.in_ids[0]]
        t1 = self.m.tensors[layer.in_ids[1]]
        if (t1.flags & 1) and not (t0.flags & 1):
            W, X = in1, in0
        else:
            W, X = in0, in1
        M = W.shape[-2] if W.ndim > 1 else 1
        K = W.shape[-1]
        N = X.shape[-2] if X.ndim > 1 else 1
        out = (X.reshape(N, K) @ W.reshape(M, K).T).reshape(N, M)
        if len(layer.in_ids) > 2 and layer.in_ids[2] >= 0:
            out = out + np.asarray(self.get(layer.in_ids[2])).reshape(1, M)
        self.put(layer.out_ids[0], out)

    def op_softmax(self, layer):
        """softmax_forward_base: softmax over the last axis."""
        x = self.inp(layer, 0).astype(np.float64)
        e = np.exp(x - x.max())
        self.put(layer.out_ids[0], e / e.sum())

    def op_lstm(self, layer):
        """lstm_forward_vanilla (evidence/ghidra/eai_rnn.c:1146-1430).

        inputs  0 x, 1 W_x [1,4H,H_in], 2 W_h [1,4H,H], 3 b_x [1,4H],
                4 b_h [1,4H], 5 h0 (optional), 6 c0 (optional), 8 cell tensor
        outputs 0 y (= h), 1 h_out (optional), 2 c_out (optional)

        Gate blocks of the 4H projection (read from the step function where
        sigmoid is applied to blocks 0..2 and tanh to block 3):
            0:H = i (sigma), H:2H = o (sigma), 2H:3H = f (sigma), 3H:4H = g (tanh)
        c = f*c_prev + i*g,  h = o*tanh(c).
        The reference adds bias 3 to the x projection and bias 4 to the recurrent
        projection, i.e. both are summed into the gate pre-activations.
        """
        x = np.asarray(self.inp(layer, 0), np.float32)
        Wx = self.inp(layer, 1)
        Wh = self.inp(layer, 2)
        H = Wx.shape[-2] // 4
        seq = x.shape[0] if x.ndim > 2 else 1
        xf = x.reshape(seq, -1)
        bx = self.get(layer.in_ids[3]) if layer.in_ids[3] >= 0 else 0.0
        bh = self.get(layer.in_ids[4]) if layer.in_ids[4] >= 0 else 0.0
        bias = (np.asarray(bx, np.float32).reshape(-1)[:4 * H]
                + np.asarray(bh, np.float32).reshape(-1)[:4 * H])
        h = np.zeros(H, np.float32)
        c = np.zeros(H, np.float32)
        if len(layer.in_ids) > 5 and layer.in_ids[5] >= 0:
            h = np.asarray(self.get(layer.in_ids[5]), np.float32).reshape(-1)[:H].copy()
        if len(layer.in_ids) > 6 and layer.in_ids[6] >= 0:
            c = np.asarray(self.get(layer.in_ids[6]), np.float32).reshape(-1)[:H].copy()
        Wx = Wx.reshape(4 * H, -1).astype(np.float32)
        Wh = Wh.reshape(4 * H, H).astype(np.float32)
        ys = np.zeros((seq, H), np.float32)
        for t in range(seq):
            pre = Wx @ xf[t] + Wh @ h + bias
            i = _sig(pre[0:H])
            o = _sig(pre[H:2 * H])
            f = _sig(pre[2 * H:3 * H])
            g = np.tanh(pre[3 * H:4 * H])
            c = f * c + i * g
            h = o * np.tanh(c)
            ys[t] = h
        cell = layer.in_ids[8] if len(layer.in_ids) > 8 else -1
        if cell >= 0:
            self.put(cell, c)
        self.put(layer.out_ids[0], ys.reshape(self.m.tensors[layer.out_ids[0]].shape))
        if len(layer.out_ids) > 1 and layer.out_ids[1] >= 0:
            self.put(layer.out_ids[1], h)
        if len(layer.out_ids) > 2 and layer.out_ids[2] >= 0:
            self.put(layer.out_ids[2], c)

    # -- ops (transcribed, not verified against the oracle) --------------
    def _elemwise(self, layer, fn):
        a = self.inp(layer, 0)
        b = self.inp(layer, 1) if len(layer.in_ids) > 1 else None
        self.put(layer.out_ids[0], fn(a, b))

    def op_add(self, layer):
        self._elemwise(layer, lambda a, b: a + b)

    def op_sub(self, layer):
        self._elemwise(layer, lambda a, b: a - b)

    def op_mul(self, layer):
        self._elemwise(layer, lambda a, b: a * b)

    def op_div(self, layer):
        self._elemwise(layer, lambda a, b: a / b)

    def op_sigmoid(self, layer):
        self.put(layer.out_ids[0], _sig(self.inp(layer, 0)))

    def op_tanh(self, layer):
        self.put(layer.out_ids[0], np.tanh(self.inp(layer, 0)))

    def op_log(self, layer):
        self.put(layer.out_ids[0], np.log(self.inp(layer, 0)))

    def op_exp(self, layer):
        self.put(layer.out_ids[0], np.exp(self.inp(layer, 0)))

    def op_sqrt(self, layer):
        self.put(layer.out_ids[0], np.sqrt(np.maximum(self.inp(layer, 0), 0.0)))

    def op_clip(self, layer):
        self.put(layer.out_ids[0], np.clip(self.inp(layer, 0),
                                          layer.float(0x10), layer.float(0x14)))

    def op_quantize(self, layer):
        self.put(layer.out_ids[0], self.inp(layer, 0))

    def op_squeeze(self, layer):
        self.put(layer.out_ids[0], np.squeeze(self.inp(layer, 0)))

    def op_unsqueeze(self, layer):
        self.put(layer.out_ids[0], np.reshape(self.inp(layer, 0),
                                              self.m.tensors[layer.out_ids[0]].shape))

    def op_reshape(self, layer):
        self.put(layer.out_ids[0], np.reshape(self.inp(layer, 0),
                                              self.m.tensors[layer.out_ids[0]].shape))

    def op_transpose(self, layer):
        n = (layer.record_size - 16) // 4
        perm = [layer.u32(0x10 + 4 * i) for i in range(n)] if n else []
        x = self.inp(layer, 0)
        self.put(layer.out_ids[0], np.transpose(x, perm) if perm and max(perm) < x.ndim else x)

    def op_concat(self, layer):
        axis = layer.u32(0x10)
        parts = [self.get(t) for t in layer.in_ids if t >= 0]
        self.put(layer.out_ids[0], np.concatenate(parts, axis=axis))

    def op_matmul(self, layer):
        self.put(layer.out_ids[0], np.matmul(self.inp(layer, 0), self.inp(layer, 1)))

    def op_logsoftmax(self, layer):
        x = self.inp(layer, 0).astype(np.float64)
        s = x - x.max()
        self.put(layer.out_ids[0], s - np.log(np.exp(s).sum()))

    def op_argmax(self, layer):
        self.put(layer.out_ids[0], np.array([np.argmax(self.inp(layer, 0))], np.float32))

    def op_reducemean(self, layer):
        self.put(layer.out_ids[0], np.full(self.m.tensors[layer.out_ids[0]].shape or (1,),
                                           np.mean(self.inp(layer, 0)), np.float32))

    def op_reduce(self, layer):
        self.put(layer.out_ids[0], np.array([np.sum(self.inp(layer, 0))], np.float32))

    def op_splat(self, layer):
        self.put(layer.out_ids[0], np.full(self.m.tensors[layer.out_ids[0]].shape,
                                           np.asarray(self.inp(layer, 0)).ravel()[0],
                                           np.float32))

    # -- execution -------------------------------------------------------
    def inp(self, layer, k):
        return self.get(layer.in_ids[k])

    def run(self, inputs=None, reset_state=False):
        """Run the graph once.  ``inputs`` maps tensor id -> array."""
        inputs = inputs or {}
        if reset_state:
            for tid in self.m.input_ids:
                self.put(tid, np.zeros(self.m.tensors[tid].shape, np.float32))
        for tid in self.m.input_ids:
            if tid in inputs:
                self.put(tid, inputs[tid])
        for layer in self.m.layers:
            if layer.flags & 0x200:
                self.skipped.append((layer.index, layer.op))
                continue
            fn = getattr(self, "op_" + layer.op, None)
            if fn is None or (layer.op in UNVERIFIED_OPS
                              and not self.allow_unverified
                              and layer.op not in VERIFIED_OPS):
                raise UnsupportedOp(layer.op, layer.index,
                                    "not implemented/verified for this runtime")
            fn(layer)
        return {tid: self.get(tid) for tid in self.m.output_ids}

    def run_to(self, layer_index, inputs, reset_state=True):
        """Run only up to (and including) ``layer_index`` - used for debugging."""
        inputs = inputs or {}
        if reset_state:
            for tid in self.m.input_ids:
                self.put(tid, np.zeros(self.m.tensors[tid].shape, np.float32))
        for tid in self.m.input_ids:
            if tid in inputs:
                self.put(tid, inputs[tid])
        for layer in self.m.layers:
            if layer.index > layer_index:
                break
            fn = getattr(self, "op_" + layer.op, None)
            if fn is None:
                raise UnsupportedOp(layer.op, layer.index)
            fn(layer)
        return self.buf


def run_file(path, inputs=None, allow_unverified=False):
    m = load_model(path)
    return EaiRunner(m, allow_unverified=allow_unverified).run(inputs)


if __name__ == "__main__":
    import sys
    m = load_model(sys.argv[1] if len(sys.argv) > 1 else
                   "models/eai/speech_float_speech_0.eai")
    r = EaiRunner(m, allow_unverified=True)
    out = r.run()
    print("%s  layers=%d  ops=%s" % (m.name, m.n_layers, m.op_histogram()))
    for tid, v in out.items():
        print("output tensor %d %s" % (tid, np.asarray(v).ravel()[:8]))
