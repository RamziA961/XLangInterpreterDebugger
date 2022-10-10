package interpreter.debugger;

import interpreter.Program;
import interpreter.VirtualMachine;


public class DebuggerVirtualMachine extends VirtualMachine {
  private Debugger debugger;

  public DebuggerVirtualMachine(Program program, Debugger debugger) {
    super(program);
    this.debugger = debugger;
  }

  //Debugging-----------------------
  //--------------------------------

  public void popEnvironmentVariable(){
    debugger.environmentVariablePop(1);
  }

  @Override
  public void newFrameAt(int offset){
    super.newFrameAt(offset);
    debugger.pushEnvironmentRecord(); //new environmentRecord created and pushed
  }

  @Override
  public void popRunStackFrame(){
    //pop function frame
    super.popRunStackFrame();
    debugger.popEnvironmentRecord();
  }

  public void loadFunctionEnvInfo(String functionName, int startLine, int endLine){
    debugger.setFunctionEnvironmentRecordsStackInformation(functionName, startLine, endLine);
  }

  public void adjustLineNumber(int lineNumber){
    debugger.setSourceCodeLineNumber(lineNumber);
    debugger.setTopRecordLineNumber(lineNumber);
  }

  public void loadFunctionFormal(String identifier, int offset){
    debugger.loadFunctionFormals(identifier, getValueAtOffset(offset) );
  }

  public void environmentVariableStore(String identifier, int value){
    debugger.environmentVariableStore(identifier, value);
  }

}