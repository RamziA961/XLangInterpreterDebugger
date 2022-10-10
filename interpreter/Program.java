package interpreter;
// Holds instances of bytecode
// Resolves symbolic addresses
import interpreter.bytecode.ByteCode;
import java.util.LinkedList;

public class Program {
  private LinkedList<ByteCode> generatedByteCodeClasses;

  public Program(){
    generatedByteCodeClasses = new LinkedList<>();
  }

   public void resolveSymbolicAddresses(){
    for(ByteCode byteCodeInstance : generatedByteCodeClasses){
     generateAddress(byteCodeInstance);
    }
  }

  private void generateAddress(ByteCode byteCode){
    int index = 0;
    if (byteCode.hasSymbolicAddress()){
      for(ByteCode byteCodeFromList : generatedByteCodeClasses){
        if(byteCodeFromList.hasSymbolicAddress()){
          if(byteCodeFromList.getByteCodeString().equals("LABEL")){
            if(byteCodeFromList.getByteCodeArgs().equals(byteCode.getByteCodeArgs())){
              byteCode.setResolvedAddress(index);
            }
          }
        }
        index++;
      }
    }
  }

  //accessors:
  //used by the interpreter to get a byteCode instance at a particular programCounter
  public ByteCode getCode(int programCounter) {
    return generatedByteCodeClasses.get(programCounter);
  }

  public ByteCode addCode(ByteCode byteCode){
    generatedByteCodeClasses.add(byteCode);
    return generatedByteCodeClasses.peekLast();
  }
}