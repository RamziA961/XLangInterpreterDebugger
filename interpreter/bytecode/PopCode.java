package interpreter.bytecode;

import interpreter.VirtualMachine;

public class PopCode extends ByteCode {
  private int nStacks;
  private String byteCodeString;

  public PopCode(){

  }

  @Override
  public void execute(VirtualMachine vm) {
    int counter = 0;
    while(counter < nStacks){
      vm.popRunStack();
      counter++;
    }
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    String argString = "";
    for(int i = 0; i < byteCodeArgs.length; i++){
      argString += byteCodeArgs[i];
    }
    this.nStacks = Integer.parseInt(argString);
  }

  @Override
  public String dumpString() {
    return String.format("%-25s", byteCodeString +" "+ nStacks);
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
