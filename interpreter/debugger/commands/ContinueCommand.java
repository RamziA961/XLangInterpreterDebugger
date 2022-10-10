package interpreter.debugger.commands;

import interpreter.debugger.Debugger;

public class ContinueCommand extends DebuggerCommand {
  private Debugger debugger;

  ContinueCommand(Debugger debugger){
    this.debugger = debugger;
  }

  @Override
  public void execute() {
    executeLine();
    while(!debugger.isCurrentLineBreakPoint()) {
      if(debugger.vmRunState()){
        debugger.vmStep();
      }else{
        break;
      }
    }
  }

  private void executeLine(){
    int sourceLine = debugger.getSourceCodeLineNumber();
    while (debugger.getSourceCodeLineNumber() == sourceLine ){
      debugger.vmStep();
    }
  }
}
