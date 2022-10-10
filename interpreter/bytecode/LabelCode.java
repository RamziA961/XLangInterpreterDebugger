package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LabelCode extends ByteCode {
  private String byteCodeString;
  private String symbolicAddress;
  private int resolvedAddress;

  public LabelCode(){

  }

  @Override
  public void execute(VirtualMachine vm) {
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
    return this.resolvedAddress;
  }
}
