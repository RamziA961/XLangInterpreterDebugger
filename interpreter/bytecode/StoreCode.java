package interpreter.bytecode;

import interpreter.VirtualMachine;

public class StoreCode extends ByteCode {
  private Integer offset;
  private String identifier;
  private String byteCodeString;
  private Integer integerStored;

  public StoreCode(){

  }

  @Override
  public void execute(VirtualMachine vm) {
    this.integerStored = vm.peekRunStack();
    vm.storeRunStackAt(offset);
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    this.offset = Integer.parseInt(getFirstArg(byteCodeArgs));
    this.identifier = getSecondArg(byteCodeArgs).trim();
  }

  @Override
  public String dumpString() {
    return String.format("%-25s%s = %d", byteCodeString + " " + offset + " " + identifier, identifier , this.integerStored );
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
    return identifier;
  }

  @Override
  public void setResolvedAddress(int resolvedAddress) {

  }

  @Override
  public Integer getResolvedAddress() {
    return null;
  }


  public String getIdentifier() {
   return this.identifier;
  }

  public int getIntegerStored(){
    return this.integerStored;
  }

}
