package interpreter.bytecode;

import interpreter.VirtualMachine;

public class FalseBranchCode extends ByteCode {
  private String byteCodeString;
  private String symbolicAddress;
  private Integer resolvedAddress;

  public FalseBranchCode(){

  }

  @Override
  public void execute(VirtualMachine vm) {
    //if 0 on top of stack then false, goto label
    if(vm.peekRunStack() == 0){
      vm.popRunStack();
      vm.skipAhead(resolvedAddress);
    }else{
      vm.popRunStack();
    }
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    this.symbolicAddress = convertArgsToString(byteCodeArgs);
  }

  @Override
  public String dumpString() {
    return String.format("%-25s", byteCodeString + " " + symbolicAddress);
  }


  @Override
  public boolean hasSymbolicAddress() {
    return true;
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
    return this.symbolicAddress;
  }

  @Override
  public void setResolvedAddress(int resolvedAddress) {
    this.resolvedAddress = resolvedAddress;
  }

  @Override
  public Integer getResolvedAddress() {
    return resolvedAddress;
  }
}
