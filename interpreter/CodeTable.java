package interpreter;

import java.util.HashMap;

public class CodeTable {
private static HashMap<String, String> codeTableMap;
                   //ByteCode  Class Name

  public static void init () {
    codeTableMap = new HashMap<>();
    codeTableMap.put("ARGS", "ArgsCode");
    codeTableMap.put("BOP", "BinOpCode");
    codeTableMap.put("CALL", "CallCode");
    codeTableMap.put("DUMP", "DumpCode");
    codeTableMap.put("FALSEBRANCH", "FalseBranchCode");
    codeTableMap.put("GOTO", "GoToCode");
    codeTableMap.put("HALT", "HaltCode");
    codeTableMap.put("LABEL", "LabelCode");
    codeTableMap.put("LIT", "LitCode");
    codeTableMap.put("LOAD", "LoadCode");
    codeTableMap.put("POP", "PopCode");
    codeTableMap.put("READ", "ReadCode");
    codeTableMap.put("RETURN", "ReturnCode");
    codeTableMap.put("STORE", "StoreCode");
    codeTableMap.put("WRITE", "WriteCode");
  }

  public static String get(String code) {
    return codeTableMap.get(code);
  }
}