package interpreter.bytecode;

import interpreter.VirtualMachine;

public class GoToCode extends ByteCode {
  private String symbolicAddress;
  private int resolvedAddress;
  private String byteCodeString;

  public GoToCode(){

  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.skipAhead(resolvedAddress);
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    this.symbolicAddress = convertArgsToString(byteCodeArgs);
  }

  @Override
  public String dumpString() {
    return String.format("%-25s", byteCodeString +" "+ symbolicAddress);
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
    return this.resolvedAddress;
  }
}
