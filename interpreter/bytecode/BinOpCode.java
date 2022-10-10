package interpreter.bytecode;

import interpreter.VirtualMachine;

public class BinOpCode extends ByteCode {
  private String operator;
  private String byteCodeString;
  private final String[] BINARY_OPERATORS = {"+", "-", "*", "/"};
  private final String[] RELATIONAL_OPERATORS = {"==", "!=" , "<", ">", "<=", ">="};
  private final String[] LOGICAL_OPERATORS = {"&", "|"};

  public BinOpCode(){

  }

  //                                        --Stack--
  //    10        +        5                 rightArg  5
  // leftArg (operator) RightArg             operator  +
  //                                         leftArg   10

  @Override
  public void execute(VirtualMachine vm) {
    boolean executed = false;

    int rightArgument = vm.popRunStack();  //top
    int leftArgument = vm.popRunStack();  // bottom

    for(String binOperator : BINARY_OPERATORS){
      if (binOperator.equals(operator)){
        vm.pushRunStack(evalBinaryOp(leftArgument, rightArgument));
        executed = true;
        break;
      }
    }
    if(!executed){
      for(String relOperator : RELATIONAL_OPERATORS){
        if (relOperator.equals(operator)){
          vm.pushRunStack(evalRelationOp(leftArgument, rightArgument));
          executed = true;
          break;
        }
      }
    }
    if(!executed){
      for (String logOperator : LOGICAL_OPERATORS){
        if (logOperator.equals(operator)){
          vm.pushRunStack(evalLogicalOp(leftArgument, rightArgument));
          break;
        }
      }
    }
    checkStateAndDump(vm);
  }

  @Override
  public void initialize(String[] byteCodeArgs) {
    this.operator = convertArgsToString(byteCodeArgs);
  }

  @Override
  public String dumpString() {
    return String.format("%-25s", byteCodeString + " " + operator);
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
    return this.byteCodeString;
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

  private int evalBinaryOp(int leftOperand, int rightOperand){
    if (operator.equals("+")){
      return leftOperand + rightOperand ;
    } else if (operator.equals("-")){
      return leftOperand - rightOperand;
    }else if (operator.equals("*")){
      return leftOperand * rightOperand;
    }else if (operator.equals("/")){
      return leftOperand / rightOperand;
    }
    System.out.println("Something Went Wrong");
    return -9999;
  }

  private int evalRelationOp(int leftOperand, int rightOperand){
    if (operator.equals("==")){
      if(leftOperand == rightOperand){
        return 1;
      }
      return 0;
    } else if(operator.equals("!=")){
      if(leftOperand != rightOperand){
        return 1;
      }
      return 0;
    } else if(operator.equals("<")){
      if(leftOperand < rightOperand){
        return 1;
      }
      return 0;
    } else if(operator.equals(">")){
      if(leftOperand > rightOperand){
        return 1;
      }
      return 0;
    } else if(operator.equals("<=")){
      if(leftOperand <= rightOperand){
        return 1;
      }
      return 0;
    } else if(operator.equals(">=")){
      if(leftOperand >= rightOperand){
        return 1;
      }
      return 0;
    }
    return -9998;
  }

  private int evalLogicalOp(int leftArgument, int rightArgument){
    if(operator.equals("&")){
      if((leftArgument == 1) && (rightArgument == 1)){
        return 1;
      }
      return 0;
    }else if(operator.equals("|")){
      if ((leftArgument == 1) || (rightArgument == 1)){
        return 1;
      }
      return 0;
    }
    return -9997;
  }
}
