package interpreter.debugger;

import java.util.HashMap;
import java.util.Stack;

public class FunctionEnvironmentRecord {
  private int startLine = 0;
  private int endLine = 0;
  private String functionName = " - ";
  private int currentLine = 0;
  private HashMap<String, Integer> symbolTable;
  private Stack<String> keyVariables;

  public void beginScope() {
    symbolTable = new HashMap<>();
    keyVariables = new Stack<>();
  }

  public void setFunctionInfo(String functionName, int startingLineNumber, int endingLineNumber) {
    this.functionName = functionName;
    this.startLine = startingLineNumber;
    this.endLine = endingLineNumber;
  }

  public void setCurrentLineNumber(int currentLineNumber) {
    this.currentLine = currentLineNumber;
  }

  public int getCurrentLine(){
    return this.currentLine;
  }


  /**
   * this method checks if the current identifier is already in the stack of identifiers
   * if the variable is not on the stack:
   * @param symbol is added to a hashMap as a key to the value given and pushed on a local key stack
   * @param value is mapped to the key given in the a hashMap
   */
  public void enter(String symbol, int value) {
    symbol = symbol.trim();
    if (!keyVariables.contains(symbol)) {
//      symbolTable.put(symbol, new Stack<>());
//      symbolTable.get(symbol).push(value);
      symbolTable.put(symbol, value);
      keyVariables.push(symbol);
    } else {
      symbolTable.replace(symbol , value);
    }
  }


  /**
   * pops a identifier from keyVariable stack; removes the key from the hashMap;
   * @param count is the number of keys to pop from the keyVariables stack.
   */
  public void pop(int count) {
    int counter = 0;
    while (counter < count) {
      if (!keyVariables.isEmpty()) {
        if (symbolTable.containsKey(keyVariables.peek())){
          symbolTable.remove(keyVariables.pop());
        }
        counter++;
      }
    }
  }

  @Override
  public String toString(){
    StringBuilder returnString = new StringBuilder("[<");
    if (keyVariables.size() > 0){
      for (String key : symbolTable.keySet()){
        returnString.append(key).append(":").append(symbolTable.get(key)).append(", ");
      }
      returnString = new StringBuilder(returnString.substring(0, returnString.toString().trim().length() - 1));
    }
    returnString.append(">, ").append(functionName).append(", ").append(startLine).append(", ").append(endLine).append(", ").append(currentLine).append("]");
    return returnString.toString();
  }

  /**
   * Utility method to test your implementation. The output should be:
   * (<>, -, -, -, -)
   * (<>, g, 1, 20, -)
   * (<>, g, 1, 20, 5)
   * (<a/4>, g, 1, 20, 5)
   * (<b/2, a/4>, g, 1, 20, 5)
   * (<b/2, a/4, c/7>, g, 1, 20, 5)
   * (<b/2, a/1, c/7>, g, 1, 20, 5)
   * (<b/2, a/4>, g, 1, 20, 5)
   * (<a/4>, g, 1, 20, 5)
   */
  public static void main(String[] args) {
    FunctionEnvironmentRecord record = new FunctionEnvironmentRecord();

    record.beginScope();
    System.out.println(record);

    record.setFunctionInfo("g", 1, 20);
    System.out.println(record);

    record.setCurrentLineNumber(5);
    System.out.println(record);

    record.enter("a", 4);
    System.out.println(record);

    record.enter("b", 2);
    System.out.println(record);

    record.enter("c", 7);
    System.out.println(record);

    record.enter("a", 1);
    System.out.println(record);

    record.pop(2);
    System.out.println(record);

    record.pop(1);
    System.out.println(record);
  }
}