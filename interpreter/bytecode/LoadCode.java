package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LoadCode extends ByteCode {
  private String byteCodeString;
  private String literalIdentifier;
  private int offset;

  public LoadCode(){

  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.loadRunStack(offset);
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    this.offset = Integer.parseInt(getFirstArg(byteCodeArgs));
    this.literalIdentifier = getSecondArg(byteCodeArgs);
  }

  @Override
  public String dumpString() {
    return String.format("%-25s%s", byteCodeString + " " + offset, "load "+ literalIdentifier);
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
    return null;
  }

  @Override
  public void setResolvedAddress(int resolvedAddress) {
    //does nothing
  }

  @Override
  public Integer getResolvedAddress() {
    return null;
  }
}
