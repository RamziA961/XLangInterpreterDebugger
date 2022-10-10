package interpreter.bytecode;

import interpreter.VirtualMachine;

public class DumpCode extends ByteCode {
  private String byteCodeString;
  private String currentState = "OFF";
  private boolean isDumping = false;

  public DumpCode(){

  }
  //Assumes ByteCodes are correct
  private void interpretState(){
    if (currentState.equals("on")){
      this.isDumping = true;
    } else if (currentState.equals("off")){
      this.isDumping = false;
    }
    return;
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.setDumpState(isDumping);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    String argument = convertArgsToString(byteCodeArgs).trim();
    argument = argument.toLowerCase();
    if(argument.equals("off") || argument.equals("on")){
      this.currentState = argument;
      interpretState();
    }
  }

  @Override
  public String dumpString() {
    return String.format("%-25s", byteCodeString +" "+currentState);
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
