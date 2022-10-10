package interpreter.debugger.commands;

import interpreter.debugger.Debugger;

public class ExitCommand extends DebuggerCommand {
  private Debugger debugger;

  ExitCommand(Debugger debugger){
    this.debugger = debugger;
  }

  @Override
  public void execute() {
    this.debugger.setIsRunning(false);
  }
}
