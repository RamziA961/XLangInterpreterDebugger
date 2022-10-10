package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class FunctionCode extends ByteCode {
  private String byteCodeString;
  private String functionName;
  private Integer startLine;
  private Integer endLine;

  @Override
  public void execute(VirtualMachine vm) {
    DebuggerVirtualMachine dVirtualMachine = (DebuggerVirtualMachine) vm;
    dVirtualMachine.loadFunctionEnvInfo(functionName, startLine, endLine);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    int argNumber = 0;
    String start = "";
    String end = "";
    this.functionName = "";

    for(String charString : byteCodeArgs){
      if (charString.equals(" ")){
        argNumber++;
      }

      if (argNumber == 0){
        this.functionName += charString;
      }else if(argNumber == 1){
        start += charString;
      }else{
        end += charString;
      }
    }
    this.startLine = Integer.parseInt(start.trim());
    this.endLine = Integer.parseInt(end.trim());
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
