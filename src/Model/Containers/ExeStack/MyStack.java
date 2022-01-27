package Model.Containers.ExeStack;

import Model.Exceptions.ContainersException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack <T> implements MyIStack<T>{
    private Stack<T> stack = new Stack<T>();
    @Override
    public T pop() throws ContainersException {
        if(!stack.isEmpty())
            return stack.pop();
        else throw new ContainersException("The stack is empty.");
    }

    @Override
    public void push(T v) {
           stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString(){
        return stack.toString();
    }

    @Override
    public List<T> getReversed() {
        List<T> l= Arrays.asList((T[]) stack.toArray());
        Collections.reverse(l);
        return l;
    }

    @Override
    public MyStack<T> deepCoppy() {
        return (MyStack<T>)stack.clone();
    }
}
