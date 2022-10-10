package interpreter.debugger.commands;

import interpreter.debugger.Debugger;

public class ListCommand extends DebuggerCommand {
  private Debugger debugger;

  ListCommand(Debugger debugger){
    this.debugger = debugger;
  }
  @Override
  public void execute() {
    debugger.listBreakPoints();
  }
}
