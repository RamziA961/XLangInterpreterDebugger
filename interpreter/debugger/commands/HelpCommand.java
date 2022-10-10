package interpreter.debugger.commands;

import interpreter.debugger.Debugger;

public class HelpCommand extends DebuggerCommand {

  HelpCommand(Debugger debugger){

  }

  @Override
  public void execute() {
    for(String commandKey : getCommandKeys()){
      System.out.println(commandKey);
    }
  }
}
