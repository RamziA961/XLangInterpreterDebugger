package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Scanner;

public class ReadCode extends ByteCode {
  private String byteCodeString;
  private Scanner userInput;

  public ReadCode() {

  }

  @Override
  public void execute(VirtualMachine vm) {
    userInput = new Scanner(System.in);
    String input = "";
    System.out.print("READ: "); //make it clear that input must be an integer
    try {                                    //attempt parse string
      input = userInput.nextLine().trim();   //get user input string
      int givenInteger = Integer.parseInt(input); //throws exception
      vm.pushRunStack(givenInteger);
    }catch(NumberFormatException numberFormatException){ //catches exception thrown by parseInt()
      if(input.equals("exit")){              //if input was exit, terminate
        userInput.close();
        System.exit(1);
      }else{
        execute(vm); //loop till exit or valid integer passed
      }
    }
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {

  }

  @Override
  public String dumpString() {
    return String.format("%-25s", byteCodeString);
  }

  @Override
  public boolean hasSymbolicAddress() {
    return false;
  }

  @Override
  public void loadByteCodeString(String byteCodeString) {
    this.byteCodeString = byteCodeString;
  }

  @Override
  public String getByteCodeString() {
    return this.byteCodeString;
  }

  @Override
  public String getByteCodeArgs() {
    return null;
  }

  @Override
  public void setResolvedAddress(int resolvedAddress) {

  }

  @Override
  public Integer getResolvedAddress() {
    return null;
  }
}
