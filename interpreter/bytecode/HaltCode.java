package interpreter.bytecode;

import interpreter.VirtualMachine;

public class HaltCode extends ByteCode {
  private String byteCodeString;
  //tells interpreter to HALT, at the end of file.

  public HaltCode(){  //public & no parameters

  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.setRunState(false);
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs){

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
