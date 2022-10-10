package interpreter.debugger.ui;

import interpreter.debugger.Debugger;
import interpreter.debugger.commands.DebuggerCommand;

import java.util.Scanner;

public class DebuggerShell {
  private Scanner userInput;
  private String commandString;

  public DebuggerShell(Debugger debugger) {
    userInput = new Scanner(System.in);
    DebuggerCommand.initializeMap(debugger);
  }

  public DebuggerCommand prompt() {
    // Create the correct command object here, based on user interaction,
    // and return
    waitForInput();
    return DebuggerCommand.getDebuggerCommand(commandString);
  }

  private void waitForInput(){
    do {
      System.out.println("Type ? for help.");
      System.out.print("> ");
      this.commandString = userInput.nextLine().toLowerCase().trim();
    }while(!DebuggerCommand.contains(commandString));
  }

}