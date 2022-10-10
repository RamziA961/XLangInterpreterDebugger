package interpreter.debugger;

import interpreter.Interpreter;
import interpreter.Program;
import interpreter.debugger.ui.DebuggerShell;

import java.util.Stack;
import java.util.Vector;

public class Debugger extends Interpreter {
  private DebuggerVirtualMachine debuggerVirtualMachine;

  private Stack<FunctionEnvironmentRecord> functionEnvironmentRecordsStack;
  private Vector<Entry> vectorOfSourceCodeEntries;
  private Integer sourceCodeLine = 1;
  private boolean isRunning;

  public Debugger(String baseFileName) {
    super(baseFileName);

    // Update to add the correct extensions to the base file name to
    // load the byte code file, as well as to load the source file
    vectorOfSourceCodeEntries = new SourceCodeLoader(baseFileName).createSourceCodeVector();
    functionEnvironmentRecordsStack = new Stack<>();
    pushEnvironmentRecord();
  }

  @Override
  public void run() {
    this.isRunning = true;
    DebuggerShell shell = new DebuggerShell(this);
    Program program = byteCodeLoader.loadCodes();
    this.debuggerVirtualMachine =  new DebuggerVirtualMachine(program, this);

    while(isRunning) {
      shell.prompt().execute();
    }
  }

  //SourceCodeLine Accessors---------------------------
  public void setSourceCodeLineNumber(int lineNumber){
    this.sourceCodeLine = lineNumber;
  }

  public int getSourceCodeLineNumber(){
    return sourceCodeLine;
  }

  //isRunning Accessors--------------------------------
  public void setIsRunning(boolean runState){
    this.isRunning = runState;
  }

  //BreakPoint Accessors-------------------------------
  public void toggleBreakPoint(int lineNumber){
    lineNumber = lineNumber - 1;
    if(vectorOfSourceCodeEntries.get(lineNumber).isBreakPoint()){
      vectorOfSourceCodeEntries.get(lineNumber).setBreakPoint(false);
    } else {
      vectorOfSourceCodeEntries.get(lineNumber).setBreakPoint(true);
    }
  }

  public boolean isCurrentLineBreakPoint(){
    if(sourceCodeLine > -1) {
      return vectorOfSourceCodeEntries.get(sourceCodeLine - 1).isBreakPoint();
    } else {
      return false;
    }
  }
  //---------------------------------------------------

  //FunctionEnvironmentRecordStack Modifier Methods:---

  public void pushEnvironmentRecord(){
    FunctionEnvironmentRecord functionEnvironmentRecord = new FunctionEnvironmentRecord();
    functionEnvironmentRecord.beginScope();
    this.functionEnvironmentRecordsStack.push(functionEnvironmentRecord);
  }

  public void setTopRecordLineNumber(int lineNumber){
    functionEnvironmentRecordsStack.peek().setCurrentLineNumber(lineNumber);
  }

  public void setFunctionEnvironmentRecordsStackInformation(String functionName, int startingLine, int endingLine){
    functionEnvironmentRecordsStack.peek().setFunctionInfo(functionName, startingLine, endingLine);
  }

  public void environmentVariablePop(int count){
    functionEnvironmentRecordsStack.peek().pop(count);
  }

  public void popEnvironmentRecord(){
    functionEnvironmentRecordsStack.pop();
  }


  public void loadFunctionFormals(String identifier , int value){
    functionEnvironmentRecordsStack.peek().enter(identifier, value);
  }

  public void environmentVariableStore (String variable, int runStackOffset){
    functionEnvironmentRecordsStack.peek().enter(variable, runStackOffset);
  }
  //---------------------------------------------------
  //printing-------------------------------------------

  public void listBreakPoints(){
    for(Entry entry: vectorOfSourceCodeEntries){
      if(entry.isBreakPoint()){
        System.out.println(entry.toString());
      }
    }
  }

  public void printSourceCode(){
    for(Entry entry : vectorOfSourceCodeEntries){
      if(functionEnvironmentRecordsStack.peek().getCurrentLine() == entry.getLineNumber()){ //current location
        System.out.println("->" + entry.toString().substring(2)); //replace whitespace with arrow
      } else {
        System.out.println(entry);
      }
    }
  }
  //---------------------------------------------------

  //vm methods

  public boolean vmRunState(){
    return this.debuggerVirtualMachine.getRunState();
  }

  public void vmStep(){
    debuggerVirtualMachine.step();
  }
}