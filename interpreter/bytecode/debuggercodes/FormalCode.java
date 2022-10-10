package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class FormalCode extends ByteCode {
  private String byteCodeString;
  private String formalName;
  private Integer offset;

  @Override
  public void execute(VirtualMachine vm) {
    DebuggerVirtualMachine dVirtualMachine = (DebuggerVirtualMachine) vm;
    dVirtualMachine.loadFunctionFormal(formalName, offset);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    boolean encounteredSpace = false;
    String offsetString = "";
    this.formalName = "";

    for(String charString : byteCodeArgs){
      if (charString.equals(" ")){
        encounteredSpace = true;
      }
      if (!encounteredSpace){
        this.formalName += charString;
      }else{
        offsetString += charString;
      }
    }
    this.offset = Integer.parseInt(offsetString.trim());
  }

  @Override
  public String dumpString() {
    return null;
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
    return byteCodeString;
  }

  @Override
  public String getByteCodeArgs() {
    return formalName;
  }

  @Override
  public void setResolvedAddress(int resolvedAddress) {}

  @Override
  public Integer getResolvedAddress() {
    return null;
  }
}
