package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerCodeTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ByteCodeLoader {
  private BufferedReader sourceReader;
  private boolean atLastLine = false;
  private String byteCodeString;
  private String[] byteCodeArgs;
  private int lineNumber = 0;

  public ByteCodeLoader(String byteCodeFile) throws IOException{ //takes string of path to the file containing bytecodes.
    sourceReader = new BufferedReader( new FileReader( "sample_files/" + byteCodeFile + ".x.cod" ));
  }

  private void closeReader() {
    try {
      sourceReader.close();
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }

  /**
   * @return nextLine from ByteCode source file
   * (Assumes last ByteCode will be "HALT")
  */
  private String readLine(){
    if(!atLastLine) {
      try {
        String thisLine = sourceReader.readLine().trim();
         lineNumber++;
        if(thisLine.equals("HALT")){
          atLastLine = true;
          closeReader();
        }
        return thisLine;

      }catch (IOException e){
        atLastLine = true;
        closeReader();
        return null;
      }
    }
    return null;
    }

  private void interpretCodeString(String lineText) {
    int indexCounter = 0;
    int firstSpaceChar = lineText.indexOf(' ');

    if (firstSpaceChar == -1) {
      this.byteCodeString = lineText;
      this.byteCodeArgs = null;
    } else {
      this.byteCodeString = lineText.substring(0, firstSpaceChar).trim();
      lineText = lineText.substring(firstSpaceChar + 1).trim();
      if (lineText != null) {
        this.byteCodeArgs = new String[lineText.length()];
        for (char character : lineText.toCharArray()) {
          this.byteCodeArgs[indexCounter] = Character.toString(character);
          indexCounter++;
        }
      }
    }
  }

  public Program loadCodes() {
    Program programInstance = new Program();
    while(!isAtLastLine()) {
      programInstance.addCode(getNextByteCode());
    }
    programInstance.resolveSymbolicAddresses();
    return programInstance;

  }

  public ByteCode getNextByteCode() {
    String lineText;
    if (!atLastLine){
      lineText = readLine();
      if (lineText != null){
        interpretCodeString(lineText);
        try {
            ByteCode byteCodeClass = (ByteCode) Class.forName("interpreter.bytecode." + DebuggerCodeTable.get(this.byteCodeString)).newInstance();
//            System.out.println(byteCodeString);
            byteCodeClass.initialize(byteCodeArgs);
            byteCodeClass.loadByteCodeString(byteCodeString);
            return byteCodeClass;
        } catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
        }
      }
    }
    return null;
  }

  public boolean isAtLastLine(){
    return atLastLine;
  }

  public int getLineNumber(){
    return lineNumber;
  }
}