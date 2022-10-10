package interpreter.bytecode;

import interpreter.VirtualMachine;

public class ReturnCode extends ByteCode {
  private String byteCodeString;
  private String symbolicAddress;
  private Integer resolvedAddress;
  private Integer valTopOfStack;
  private String functionName;

  public ReturnCode(){

  }

  @Override
  public void execute(VirtualMachine vm) {
    this.valTopOfStack = vm.peekRunStack();
    vm.popRunStackFrame();
    vm.returnToPriorAddress();
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    if(byteCodeArgs != null){
      this.symbolicAddress = convertArgsToString(byteCodeArgs);
      if(symbolicAddress.contains("<<")) {
        this.functionName = symbolicAddress.substring(0, symbolicAddress.indexOf("<"));
      }else{
        this.functionName = symbolicAddress;
      }
    }else{
      this.functionName = null;
    }
  }

  @Override
  public String dumpString() {
    if (this.symbolicAddress !=null ) {
      return String.format("%-24s exit %s: (%d)", this.byteCodeString + " " + this.symbolicAddress, this.functionName , this.valTopOfStack );
    }else{
      return String.format("%-25s", this.byteCodeString);
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
    return symbolicAddress;
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
