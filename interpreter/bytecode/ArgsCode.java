package interpreter.bytecode;

import interpreter.VirtualMachine;

public class ArgsCode extends ByteCode {
  private int argumentsExpected;
  private String byteCodeString;

  public ArgsCode(){

  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.newFrameAt(argumentsExpected);
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    String argString = "";
    for(String charString: byteCodeArgs){
      argString += charString;
    }
    this.argumentsExpected =  Integer.parseInt(argString);

  }

  @Override
  public String dumpString() {
    return String.format("%-25s", byteCodeString + " " + argumentsExpected);
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
    return "" + argumentsExpected;
  }

  @Override
  public void setResolvedAddress(int resolvedAddress) {

  }

  @Override
  public Integer getResolvedAddress() {
    return null;
  }
}
