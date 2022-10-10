package interpreter.bytecode.debuggercodes;
import interpreter.VirtualMachine;
import interpreter.bytecode.StoreCode;
import interpreter.debugger.DebuggerVirtualMachine;

public class DebugStoreCode extends StoreCode {

  public void execute(VirtualMachine virtualMachine){
    DebuggerVirtualMachine debuggerVirtualMachine = (DebuggerVirtualMachine) virtualMachine;
    debuggerVirtualMachine.environmentVariableStore(getIdentifier(), debuggerVirtualMachine.peekRunStack());
    super.execute(virtualMachine);
  }
}


