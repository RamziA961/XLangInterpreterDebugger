package interpreter;

import java.util.Stack;
import java.util.Vector;

public class RunTimeStack {

  private Stack<Integer> framePointers;  //records location of frames.
  private Vector<Integer> runStack;

  public RunTimeStack() {
    this.runStack = new Vector<>();
    this.runStack.setSize(0);
    this.framePointers = new Stack<>();
    this.framePointers.push(0);
  }

  /**
   * The purpose of this function is to dump the RunTimeStack for the 
   * purpose of debugging.
   */
  public void dump() {
    int index = 0;
    int frame = 1;
    String stackState = "[ ";

    while (index < runStack.size()){
      if(framePointers.size() > frame) {
        if (framePointers.get(frame) == index) {
          stackState = stackState.trim().substring(0, stackState.length()-2);
          stackState += " ] [ ";
          frame++;
        }
      }
      stackState += runStack.get(index) + ", ";
      index++;
    }
    if (stackState.length() == 2) {
      stackState = stackState.trim();
    }else{
      stackState = stackState.substring(0, stackState.length()-2);
    }
    stackState += " ]";
    System.out.println(stackState);
  }

  /**
   * Returns the top item on the runtime stack.
   */
  public int peek() {
    return runStack.get(runStack.size()-1);
  }

  /**
   * Pops the top item from the runtime stack, returning the item.
   */
  public int pop() {
    return runStack.remove(runStack.size()-1);
  }

  /**
   * Push an item on to the runtime stack, returning the item that was just 
   * pushed.
   */
  public int push(int item) {
    runStack.addElement(item);
    return peek();
  }

  /**
   * This second form with an Integer parameter is used to load literals onto the
   * stack.
   */
  public Integer push(Integer integer) {
    runStack.addElement(integer);
    return peek();
  }
  /**
   * Start a new frame, where the parameter offset is the number of slots
   * down from the top of the RunTimeStack for starting the new frame.
   */
  public void newFrameAt(int offset) {
    framePointers.push((runStack.size()-1) - offset); //pushes offset of new frame into frame pointers
  }

  /**
   * We pop the top frame when we return from a function; before popping, the
   * functions’ return value is at the top of the stack so we’ll save the value,
   * pop the top frame, and then push the return value.
   */
  public void popFrame() {
    Integer valOnTop = pop();
//    System.out.println(framePointers.peek() + "\n" + runStack.size());
    while(framePointers.peek() != (runStack.size())){
      pop();
    }
    push(valOnTop); //return top value
    framePointers.pop(); //discard of frame address;
  }

  /**
   * Used to store values into variables.
   */
  public int store(int offset) {
    runStack.set(framePointers.peek() + offset, peek());
    if (framePointers.peek() + offset <= runStack.size()-1){
      pop();
    }
    return runStack.get(framePointers.peek() + offset);
  }

  /**
   * Used to load variables onto the stack.
   */
  public int load(int offset) {
    return push(runStack.get(framePointers.peek() + offset));
  }

  public int getValueAt(int offset){
    return runStack.get(framePointers.peek() + offset);
  }

}