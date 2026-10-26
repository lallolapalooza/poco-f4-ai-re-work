#!/usr/bin/env python3
"""Breadth-first call-graph reachability over direct BL edges in an aarch64 ELF.

Only direct `bl` branches are followed (indirect calls and PLT stubs are not
expanded), which is enough to answer "is function X in the direct call graph of
root R within N levels?".

Usage:
  reach.py lib.so 0x68bc04 0x9e4f80 0x9a0100 --depth 4
Prints the BFS tree path for every hit and a summary.
"""
import struct
import sys

import lief


def main(argv):
    so = argv[1]
    rest = argv[2:]
    depth = 4
    if "--depth" in rest:
        i = rest.index("--depth")
        depth = int(rest[i + 1])
        rest = rest[:i] + rest[i + 2:]
    root = int(rest[0], 16)
    targets = [int(x, 16) for x in rest[1:]]

    b = lief.parse(so)
    text = b.get_section(".text")
    data = bytes(text.content)
    base = text.virtual_address
    n = len(data) // 4
    words = struct.unpack_from("<%dI" % n, data, 0)
    edges = {}
    for i in range(n):
        w = words[i]
        if (w & 0xFC000000) != 0x94000000:  # bl only
            continue
        imm = w & 0x3FFFFFF
        if imm & (1 << 25):
            imm -= 1 << 26
        pc = base + i * 4
        edges.setdefault(pc, []).append(pc + imm * 4)

    starts = sorted(edges)
    import bisect
    from collections import deque

    def edges_between(lo, hi):
        out = []
        i = bisect.bisect_left(starts, lo)
        while i < len(starts) and starts[i] < hi:
            out.append(starts[i])
            i += 1
        return out

    def callers_of_range(lo, hi):
        res = []
        for pc in starts:
            for t in edges[pc]:
                if lo <= t < hi:
                    res.append((pc, t))
        return sorted(res)

    # BFS over bl edges; a node is a call target, its outgoing edges are the bl
    # sites in [node, node+0x20000).
    seen = {root: None}
    q = deque([(root, 0)])
    hits = {}
    while q and len(seen) < 20000:
        node, d = q.popleft()
        if d >= depth:
            continue
        for pc in edges_between(node, node + 0x20000):
            for t in edges[pc]:
                if t in seen:
                    continue
                seen[t] = (node, pc)
                if any(abs(t - x) < 0x1000 for x in targets):
                    hits[t] = (node, pc)
                q.append((t, d + 1))
    print("nodes_visited", len(seen))
    for t in sorted(hits):
        path = []
        cur = t
        while cur is not None:
            path.append(cur)
            cur = seen[cur][0] if seen[cur] else None
        print("reach 0x%x  path=%s" % (t, " <- ".join("0x%x" % p for p in path)))
    if not hits:
        print("no direct-bl path within depth", depth)
    print("--- direct callers of the target ranges ---")
    for x in targets:
        for pc, t in callers_of_range(x - 0x400, x + 0x400)[:20]:
            print("caller=0x%x -> 0x%x" % (pc, t))
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv))
