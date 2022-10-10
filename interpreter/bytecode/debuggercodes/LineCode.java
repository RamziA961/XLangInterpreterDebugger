package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class LineCode extends ByteCode {
  private String byteCodeString;
  private Integer lineNumber;

  @Override
  public void execute(VirtualMachine vm) {
    DebuggerVirtualMachine debuggerVirtualMachine = (DebuggerVirtualMachine) vm;
    debuggerVirtualMachine.adjustLineNumber(lineNumber);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    String byteCodeArgument = "";
    for (String charString : byteCodeArgs){
     byteCodeArgument += charString;
    }
    if (byteCodeArgs.length > 0){
      lineNumber = Integer.parseInt(byteCodeArgument);
    }
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
