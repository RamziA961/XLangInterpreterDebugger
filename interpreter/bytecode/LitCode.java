package interpreter.bytecode;

import interpreter.VirtualMachine;

public class LitCode extends ByteCode {
  private String literalIdentifier = "";
  private String byteCodeString;
  private int literaryValue;

  public LitCode(){

  }

  //accessor:
  public String getLiteralIdentifier(){
    if(this.literalIdentifier == null){
      return null;
    }
    return this.literalIdentifier;
  }

  public int getLiteraryValue(){
    return this.literaryValue;
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.pushRunStack(literaryValue);
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
   this.literaryValue = Integer.parseInt(getFirstArg(byteCodeArgs));
   this.literalIdentifier = getSecondArg(byteCodeArgs);
   if (this.literalIdentifier != null){
     this.literalIdentifier = this.literalIdentifier.trim();
   }
  }

  @Override
  public String dumpString() {
    if(this.literalIdentifier == null){
      return String.format("%-25s%s", byteCodeString + " " +literaryValue, "int "+ literaryValue);
    }
    return String.format("%-25s%s", byteCodeString + " " + literaryValue, "int "+ literalIdentifier);
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
