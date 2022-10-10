/**
 * DO NOT provide a method that returns components contained WITHIN the VM (this 
 * is the exact situation that will break encapsulation) - you should request 
 * that the VM performs operations on its components. This implies that the VM 
 * owns the components and is free to change them, as needed, without breaking 
 * clients' code (e.g., suppose I decide to change the name of the variable that 
 * holds my runtime stack - if your code had referenced that variable then your 
 * code would break. This is not an unusual situation - you can consider the 
 * names of methods in the Java libraries that have been deprecated).
 * 
 * Consider that the VM calls the individual ByteCodes' execute method and 
 * passes itself as a parameter. For the ByteCode to execute, it must invoke 
 * one or more methods in the runStack. It can do this by executing 
 * VM.runStack.pop(); however, this does break encapsulation. To avoid this, 
 * you'll need to have a corresponding set of methods within the VM that do 
 * nothing more than pass the call to the runStack. e.g., you would want to 
 * define a VM method:
 *     public int popRunStack() {
 *       return runStack.pop();
 *     }
 * called by, e.g.,
 *     int temp = VM.popRunStack();
 */
package interpreter;

import java.util.Stack;
import interpreter.bytecode.ByteCode;

public class VirtualMachine {

  private int pc;
  private RunTimeStack runTimeStack;
  // This may not be the right parameterized type!!
  private Stack<Integer> returnAddresses;
  private boolean isRunning;
  private Program program;
  private boolean dumpState = false;

  public VirtualMachine(Program program) {
    this.program = program;
    pc = 0;
    runTimeStack = new RunTimeStack();
    returnAddresses = new Stack<>();
    isRunning = true;
  }

  public void executeProgram() {
    while (isRunning) {
      ByteCode code = program.getCode(pc);
      code.execute(this);

      if(dumpState && !code.getByteCodeString().equals("DUMP")){
        runTimeStack.dump();
      }
      // runTimeStack.dump(); // check that the operation is correct
      pc++;
    }
  }


  //------------------------------------------------
  //runStack accessors:
  public int popRunStack(){
    return runTimeStack.pop();
  }

  public int storeRunStackAt(int offset){ //STORE
    return runTimeStack.store(offset);
  }

  public int peekRunStack(){
    return runTimeStack.peek();
  }

  public int pushRunStack(int item){
    return runTimeStack.push(item);
  }

  public int pushRunStack(Integer item){
    return runTimeStack.push(item);
  }

  public void newFrameAt(int offset){ //ARGS
    runTimeStack.newFrameAt(offset - 1);
  }

  public void popRunStackFrame(){
    runTimeStack.popFrame();
  }

  public void loadRunStack(int offset) {  //LOAD
    runTimeStack.load(offset);
  }

  //dumpState switch
  public void setDumpState(boolean dumpState){ //DUMP
    this.dumpState = dumpState;
  }

  public boolean getDumpState(){
    return dumpState;
  }

  //isRunning switch
  public void setRunState(boolean isRunning){ //HALT
    this.isRunning = isRunning;
  }

  public boolean getRunState(){
    return this.isRunning;
  }

  //move to different addresses:
  public void goToAddress(Integer address){ //go to label; push return address ( function call )
    returnAddresses.push(pc);
    pc = address-1;
  }
  public void returnToPriorAddress(){
    pc = returnAddresses.pop();
  } //return to address after function call

  public void skipAhead(Integer address){
    pc = address - 1;
  } //skip to certain address (no return address)

  //get call function parameters from stack
  public String getCallFunctionParameters() {
    int argumentsExpected = Integer.parseInt(program.getCode(pc - 1).getByteCodeArgs()); //get number from ARGS class
    if (argumentsExpected > 0) {
      String callFunctionParameters = "";
      for (int i = 0; i < argumentsExpected; i++) { //get parameters from stack
        callFunctionParameters += runTimeStack.getValueAt(i) + ", ";
      }
      return callFunctionParameters.substring(0, callFunctionParameters.length() - 2);
    }
    return " ";
  }

  public void step(){
    if (this.isRunning) {
      program.getCode(pc).execute(this);
      pc++;
      if(!this.isRunning){ //only executes on last bytecode (HALT)
        pc--;
      }
    }
  }

  public int getValueAtOffset(int offset){
    return runTimeStack.getValueAt(offset);
  }

  //Debugging-----------------------

  public void dumpStack(){
    runTimeStack.dump();
  }

  public int getPC(){
    return this.pc;
  }

  //-------------------------------
}