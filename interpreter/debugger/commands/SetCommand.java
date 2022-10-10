package interpreter.debugger.commands;

import interpreter.debugger.Debugger;

import java.util.Scanner;

public class SetCommand extends DebuggerCommand {
  private Scanner setInput;
  private Debugger debugger;


  SetCommand(Debugger debugger){
    setInput = new Scanner(System.in);
    this.debugger = debugger;
  }

  @Override
  public void execute() {
    debugger.toggleBreakPoint(setInput.nextInt());
  }
}
