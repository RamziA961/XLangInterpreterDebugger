/**
 * Each bytecode class should have fields for its specific arguments. The 
 * abstract ByteCode class SHOULD NOT CONTAIN ANY FIELDS (instance variables) 
 * THAT RELATE TO ARGUMENTS. This is a design requirement.
 * 
 * It's easier to think in more general terms (i.e. plan for any number of 
 * args for a bytecode). Note that the Bytecode abstract class should not be 
 * aware of the peculiarities of any particular bytecode. That is, some 
 * bytecodes might have zero args (HALT), or one arg, etc. Consider providing 
 * an "init" method with each bytecode class. After constructing the bytecode 
 * (e.g. LoadCode) then you can call its "init" method and give it a vector of 
 * String args. Each bytecode object will interrogate the vector and extract 
 * relevant args for itself. The Bytecode class SHOULD NOT record the args 
 * for any bytecodes. Each concrete bytecode class will have instance 
 * variable(s) to record its args.
 * 
 * When you read a line from the bytecode file, you should parse each argument 
 * placing them into a vector. Each bytecode takes responsibility for extracting
 * relevant information from the vector and storing it as private data.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;

public abstract class ByteCode {

  public abstract void execute(VirtualMachine vm);

  public abstract void initialize(String[] byteCodeArgs);

  public abstract String dumpString();

  public abstract boolean hasSymbolicAddress();

  public abstract void loadByteCodeString(String byteCodeString);

  public abstract String getByteCodeString();

  public abstract String getByteCodeArgs();

  public abstract void setResolvedAddress(int resolvedAddress);

  public abstract Integer getResolvedAddress();


  String convertArgsToString(String[] byteCodeArgs) {
    String address = "";
    for (String argLetter : byteCodeArgs) {
      address += argLetter;
    }
    return address;
  }


  //2 variable arguments : store, load, lit
  String getFirstArg(String[] byteCodeArgs) {
    String firstArg = "";
    if (byteCodeArgs!=null) {
      int index = 0;
      while (index < byteCodeArgs.length) {
        if (byteCodeArgs[index].equals(" ")) {
          break;
        }
        firstArg += byteCodeArgs[index];
        index++;
      }
      return firstArg;
    }
    return null;
    }

  String getSecondArg(String[] byteCodeArgs) {
    String secondArg = "";
    boolean encounteredSpace = false;
    if(byteCodeArgs != null) {
      int index = 0;
      while (index < byteCodeArgs.length) {
        if (byteCodeArgs[index].equals(" ")) {
          encounteredSpace = true;
        }
        if (encounteredSpace) {
          secondArg += byteCodeArgs[index];
        }
        index++;
      }
    }
    if (encounteredSpace) {
      return secondArg;
    } else {
      return null;
    }
  }

  void checkStateAndDump(VirtualMachine vm){
    if (vm.getDumpState()){
      System.out.println(dumpString());
    }
  }
}