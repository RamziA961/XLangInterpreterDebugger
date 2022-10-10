package interpreter.debugger.commands;

import interpreter.debugger.Debugger;

public class SourceCommand extends DebuggerCommand{
  private Debugger debugger;

  SourceCommand(Debugger debugger){
    this.debugger = debugger;
  }

  @Override
  public void execute() {
    debugger.printSourceCode();
  }
}
