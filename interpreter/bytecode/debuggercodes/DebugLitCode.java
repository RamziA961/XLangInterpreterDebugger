package interpreter.bytecode.debuggercodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.LitCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class DebugLitCode extends LitCode {

  @Override
  public void execute(VirtualMachine virtualMachine){
    if(super.getLiteralIdentifier() != null){
      DebuggerVirtualMachine debuggerVirtualMachine = (DebuggerVirtualMachine) virtualMachine;
      debuggerVirtualMachine.environmentVariableStore(getLiteralIdentifier(), getLiteraryValue());
    }
    super.execute(virtualMachine);
  }
}
