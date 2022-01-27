package Model.Containers.ExeStack;

import Model.Exceptions.ContainersException;

import java.util.List;

public interface MyIStack <T> {
    public T pop() throws ContainersException;
    public void push(T v);
    public boolean isEmpty();
    public List<T> getReversed() ;
    public MyStack<T> deepCoppy();
}
