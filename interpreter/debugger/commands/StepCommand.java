package interpreter.debugger.commands;

import interpreter.debugger.Debugger;

public class StepCommand extends DebuggerCommand {
  private Debugger debugger;

  StepCommand(Debugger debugger){
    this.debugger = debugger;
  }

  @Override
  public void execute() {
    debugger.vmStep();
  }
}
