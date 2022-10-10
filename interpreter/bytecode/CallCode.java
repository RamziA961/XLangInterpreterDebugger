package interpreter.bytecode;

import interpreter.VirtualMachine;

public class CallCode extends ByteCode {
  private String symbolicAddress;
  private int resolvedAddress;
  private String byteCodeString;
  private String functionParameters;
  private String functionName;

  public  CallCode(){

  }

  @Override
  public void execute(VirtualMachine vm) {
    this.functionParameters = vm.getCallFunctionParameters();
    vm.goToAddress(resolvedAddress);
    if (vm.getDumpState()){
      System.out.println(dumpString());
    }
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    this.symbolicAddress = convertArgsToString(byteCodeArgs);
    if(this.symbolicAddress.contains("<<")) {
      this.functionName = this.symbolicAddress.substring(0, this.symbolicAddress.indexOf('<'));
    }else{
      this.functionName = this.symbolicAddress;
    }
  }

  @Override
  public String dumpString() {
    if(!symbolicAddress.equals(functionName)) {
      return String.format("%-25s%s(%s)", byteCodeString + " " + this.symbolicAddress, functionName, functionParameters);
    }else{
      return String.format("%-25s",byteCodeString + " " + this.symbolicAddress);
    }
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
