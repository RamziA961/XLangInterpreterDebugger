package interpreter.debugger;

import java.util.HashMap;

import interpreter.CodeTable;

public class DebuggerCodeTable {
  private static HashMap<String, String> codeMap = new HashMap<>();

  public static void init() {
    CodeTable.init();
    codeMap.put("FUNCTION","debuggercodes.FunctionCode");
    codeMap.put("FORMAL", "debuggercodes.FormalCode");
    codeMap.put("LINE", "debuggercodes.LineCode");
    codeMap.put("STORE", "debuggercodes.DebugStoreCode");
    codeMap.put("POP", "debuggercodes.DebugPopCode");
    codeMap.put("LIT", "debuggercodes.DebugLitCode");

    // Initialize the new byte codes required for debugging:
    // codeMap.put(... etc. ...)
  }

  public static String get(String code) {
    String result = codeMap.get(code.trim().toUpperCase());

    if(result == null) {
      return CodeTable.get(code);
    } else {
      return result;
    }
  }
}