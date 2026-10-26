// DecompileAt.java - headless Ghidra post-script.
//
// Decompiles the function containing each given ELF-relative address, then
// optionally its callees up to a given depth (breadth-first, deduplicated).
//
// Script args:
//   0: output .c file (required)
//   1: comma-separated ELF-relative addresses in hex, e.g. "0x68bc04,0x9e4f80"
//   2: callee depth (default 1, 0 = only the containing functions)
//   3: maximum number of functions to decompile (default 40)

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ghidra.app.decompiler.DecompInterface;
import ghidra.app.decompiler.DecompileResults;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.address.Address;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionManager;

public class DecompileAt extends GhidraScript {

    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length < 2) {
            printerr("DecompileAt: need <out.c> <hexaddr,...>");
            return;
        }
        String outPath = args[0];
        String[] addrs = args[1].split(",");
        int depth = args.length > 2 ? Integer.parseInt(args[2].trim()) : 1;
        int maxFns = args.length > 3 ? Integer.parseInt(args[3].trim()) : 40;

        FunctionManager fm = currentProgram.getFunctionManager();
        Address base = currentProgram.getImageBase();
        println("base=" + base);

        Map<Function, Integer> order = new LinkedHashMap<>();
        ArrayDeque<Object[]> queue = new ArrayDeque<>();
        for (String s : addrs) {
            String t = s.trim();
            if (t.isEmpty()) continue;
            long off = Long.parseLong(t.replaceFirst("^0[xX]", ""), 16);
            Address a = base.add(off);
            Function f = fm.getFunctionContaining(a);
            println("addr " + t + " -> " + (f == null ? "no function" : f.getName()));
            if (f != null) queue.add(new Object[]{f, 0});
        }
        while (!queue.isEmpty() && order.size() < maxFns) {
            Object[] item = queue.poll();
            Function f = (Function) item[0];
            int d = (Integer) item[1];
            if (order.containsKey(f)) continue;
            order.put(f, d);
            if (d < depth) {
                for (Function c : f.getCalledFunctions(monitor)) {
                    if (!order.containsKey(c) && c.isThunk() == false) {
                        queue.add(new Object[]{c, d + 1});
                    }
                }
            }
        }

        DecompInterface ifc = new DecompInterface();
        ifc.toggleCCode(true);
        ifc.toggleSyntaxTree(true);
        ifc.openProgram(currentProgram);

        List<String> matched = new ArrayList<>();
        try (PrintWriter pw = new PrintWriter(new File(outPath))) {
            pw.println("/* Ghidra DecompileAt: " + order.size() + " function(s) */");
            for (Map.Entry<Function, Integer> e : order.entrySet()) {
                Function f = e.getKey();
                DecompileResults res = ifc.decompileFunction(f, 60, monitor);
                pw.println();
                pw.println("/* ===== Function: " + f.getName() + " @ " + f.getEntryPoint()
                        + "  size=" + f.getBody().getNumAddresses() + "  depth=" + e.getValue());
                pw.println("   signature: " + f.getPrototypeString(true, true) + " ===== */");
                if (res != null && res.decompileCompleted()) {
                    pw.println(res.getDecompiledFunction().getC());
                }
                else {
                    pw.println("// decompile failed: "
                            + (res == null ? "null" : res.getErrorMessage()));
                }
                matched.add(f.getName());
            }
        }
        println("SUMMARY decompiled=" + order.size() + " names=" + matched);
    }
}
