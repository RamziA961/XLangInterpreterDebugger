package interpreter.debugger;

public class Entry {
  private int lineNumber;
  private String lineNumSourceCode;
  private boolean isBreakPoint;

  Entry(int lineNumber, String lineNumSourceCode, boolean isBreakPoint){
    this.lineNumber = lineNumber;
    this.lineNumSourceCode = lineNumSourceCode;
    this.isBreakPoint = isBreakPoint;
  }

  void setBreakPoint(boolean isBreakPoint){
    this.isBreakPoint = isBreakPoint;
  }

  boolean isBreakPoint(){
    return isBreakPoint;
  }

  int getLineNumber(){
    return this.lineNumber;
  }


  @Override
  public String toString(){
    if(isBreakPoint){
      return String.format("   * %-3d: %-25s",lineNumber, lineNumSourceCode);
    }
    return String.format("     %-3d: %-25s",lineNumber, lineNumSourceCode);
  }
}
