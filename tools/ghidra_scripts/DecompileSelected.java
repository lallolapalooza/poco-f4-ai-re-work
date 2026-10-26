// DecompileSelected.java - headless Ghidra post-script.
//
// Script args:
//   0: output .c file (required)
//   1: Java regex matched against function name and fully qualified name
//   2: maximum number of functions to decompile (default 64, 0 = unlimited)
//   3: per-function decompiler timeout in seconds (default 60)
//
// Writes C pseudocode separated by /* Function: ... @ addr */ banners and
// prints a one-line SUMMARY for the wrapper.

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import ghidra.app.script.GhidraScript;
import ghidra.app.decompiler.DecompInterface;
import ghidra.app.decompiler.DecompileResults;
import ghidra.program.model.address.Address;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionIterator;

public class DecompileSelected extends GhidraScript {

    private static final String DEFAULT_REGEX =
        "Java_.*|JNI_OnLoad|.*[Mm]odel.*|.*[Ii]nfer.*";

    @Override
    public void run() throws Exception {
        String[] args = getScriptArgs();
        if (args.length < 1) {
            printerr("DecompileSelected: first argument must be the output file");
            return;
        }
        String outPath = args[0];
        String regex = args.length > 1 && !args[1].isEmpty() ? args[1] : DEFAULT_REGEX;
        int maxFunctions = 64;
        if (args.length > 2) {
            try {
                maxFunctions = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                printerr("DecompileSelected: bad max-functions '" + args[2] + "'");
            }
        }
        int perFunctionTimeout = 60;
        if (args.length > 3) {
            try {
                perFunctionTimeout = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                printerr("DecompileSelected: bad timeout '" + args[3] + "'");
            }
        }

        Pattern pattern;
        try {
            pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            printerr("DecompileSelected: invalid regex: " + e.getMessage());
            return;
        }

        File outFile = new File(outPath);
        File parent = outFile.getAbsoluteFile().getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            printerr("DecompileSelected: cannot create output directory " + parent);
            return;
        }

        DecompInterface decompiler = new DecompInterface();
        decompiler.setSimplificationStyle("decompile");
        decompiler.toggleCCode(true);
        decompiler.toggleSyntaxTree(false);
        if (!decompiler.openProgram(currentProgram)) {
            printerr("DecompileSelected: could not open program in decompiler: "
                + decompiler.getLastMessage());
            decompiler.dispose();
            return;
        }

        int matched = 0;
        int attempted = 0;
        int decompiled = 0;
        int failed = 0;
        int timedOut = 0;
        boolean truncated = false;

        PrintWriter out = new PrintWriter(new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(outFile, false),
                StandardCharsets.UTF_8)));
        try {
            out.println("/*");
            out.println(" * Ghidra headless decompilation");
            out.println(" * program   : " + currentProgram.getName());
            out.println(" * executable: " + currentProgram.getExecutablePath());
            out.println(" * language  : " + currentProgram.getLanguageID());
            out.println(" * imagebase : " + currentProgram.getImageBase());
            out.println(" * regex     : " + regex);
            out.println(" * max funcs : " + (maxFunctions > 0 ? maxFunctions : "unlimited"));
            out.println(" * note      : pseudocode from a (possibly stripped) binary is approximate;");
            out.println(" *             indirect calls and register-indirect JNI tables are lost.");
            out.println(" */");
            out.println();

            FunctionIterator functions =
                currentProgram.getFunctionManager().getFunctions(true);
            while (functions.hasNext()) {
                if (monitor.isCancelled()) {
                    println("DecompileSelected: cancelled by monitor");
                    break;
                }
                Function function = functions.next();
                String shortName = function.getName();
                String fullName = function.getName(true);
                if (!pattern.matcher(shortName).find()
                        && !pattern.matcher(fullName).find()) {
                    continue;
                }
                matched++;
                if (maxFunctions > 0 && attempted >= maxFunctions) {
                    truncated = true;
                    continue;
                }
                attempted++;

                Address entry = function.getEntryPoint();
                long bodySize = function.getBody().getNumAddresses();
                out.println("/* ------------------------------------------------------------------");
                out.println(" * Function: " + fullName);
                out.println(" * Address : " + entry);
                out.println(" * Size    : 0x" + Long.toHexString(bodySize)
                    + " bytes");
                out.println(" * ------------------------------------------------------------------ */");

                DecompileResults results;
                try {
                    results = decompiler.decompileFunction(
                        function, perFunctionTimeout, monitor);
                } catch (Exception e) {
                    failed++;
                    out.println("/* decompiler exception: " + e.getMessage() + " */");
                    out.println();
                    continue;
                }
                if (results != null && results.decompileCompleted()
                        && results.getDecompiledFunction() != null) {
                    String code = results.getDecompiledFunction().getC();
                    if (code == null || code.trim().isEmpty()) {
                        failed++;
                        out.println("/* decompiler returned empty output */");
                    } else {
                        decompiled++;
                        out.println(code);
                    }
                } else {
                    if (results != null && results.isTimedOut()) {
                        timedOut++;
                    } else {
                        failed++;
                    }
                    String why = results == null ? "no result"
                        : (results.isTimedOut() ? "timed out after "
                            + perFunctionTimeout + "s"
                            : results.getErrorMessage());
                    out.println("/* decompilation failed: " + why + " */");
                }
                out.println();
                out.flush();
            }
        } finally {
            out.close();
            decompiler.dispose();
        }

        println("SUMMARY matched=" + matched + " attempted=" + attempted
            + " decompiled=" + decompiled + " failed=" + failed
            + " timedout=" + timedOut + " truncated=" + truncated
            + " output=" + outFile.getAbsolutePath());
    }
}
