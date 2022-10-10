package interpreter.debugger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class SourceCodeLoader {
  private BufferedReader sourceReader;
  private boolean atLastLine = false;
  private int lineNumber = 0;

  public SourceCodeLoader(String sourceCodeFile){
    try {
      sourceReader = new BufferedReader(new FileReader("sample_files/"+sourceCodeFile + ".x"));
    }catch (IOException ioException){
      ioException.printStackTrace();
    }
  }

  private String readLine() {
    if (!atLastLine) {
      try {
        String thisLine = sourceReader.readLine().trim(); //hits exception at end of file
        lineNumber++;
        return thisLine;

      } catch (IOException e) {
        e.printStackTrace();
        return null;
      } catch (NullPointerException outOfBounds){
        atLastLine = true;
        return null;
      }
    }
    return null;
  }

  Vector<Entry> createSourceCodeVector(){
    Vector<Entry> sourceCodeVector = new Vector<>();
    sourceCodeVector.setSize(0);
    while (!atLastLine){
      String sourceLine = readLine();
      if(!atLastLine){
        sourceCodeVector.addElement(new Entry(lineNumber, sourceLine, false));
      }
    }
    return sourceCodeVector;
  }

  public static void main(String[] args){
   SourceCodeLoader sourceCodeLoader = new SourceCodeLoader(args[0]);
   for (Entry entry : sourceCodeLoader.createSourceCodeVector()){
    System.out.println(entry);
   }
  }
}