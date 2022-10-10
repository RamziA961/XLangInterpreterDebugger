package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.PopCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class DebugPopCode extends PopCode {

  @Override
  public void execute(VirtualMachine virtualMachine){
    DebuggerVirtualMachine debuggerVirtualMachine = (DebuggerVirtualMachine) virtualMachine;
    debuggerVirtualMachine.popEnvironmentVariable();
    super.execute(virtualMachine);
  }

}
