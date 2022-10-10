package interpreter.debugger.commands;



import interpreter.debugger.Debugger;

import java.util.HashMap;
import java.util.Set;

public abstract class DebuggerCommand {

  private static HashMap<String, DebuggerCommand> mapOfCommands;

  public static void initializeMap(Debugger debugger) {
    mapOfCommands = new HashMap<>();
    mapOfCommands.put("?", new HelpCommand(debugger));
    mapOfCommands.put("continue", new ContinueCommand(debugger));
    mapOfCommands.put("exit", new ExitCommand(debugger));
    mapOfCommands.put("list", new ListCommand(debugger));
    mapOfCommands.put("set", new SetCommand(debugger));
    mapOfCommands.put("source", new SourceCommand(debugger));
    mapOfCommands.put("step", new StepCommand(debugger));
  }

  public static boolean contains(String commandKey){
    return mapOfCommands.containsKey(commandKey);
  }

  public static DebuggerCommand getDebuggerCommand(String commandString){
    return mapOfCommands.get(commandString);
  }

  Set<String> getCommandKeys(){
    return mapOfCommands.keySet();
  }

  public abstract void execute();
}