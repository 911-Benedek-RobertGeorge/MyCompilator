package Model.Containers.Heap;

import Model.Containers.ExeStack.MyStack;
import Model.Containers.SymTable.MyDictionary;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ContainersException;

import java.util.Map;

public interface MyIHeap<Integer,Value> {
    public void add(Integer key, Value value) throws ContainersException;
    public int getFirstFreeLocation();
    public void setContent(Map<Integer,Value> refreshedHeap);
    public Map<Integer,Value>  getContent();
    Value getValueOnAddress(Integer address);

    public MyIHeap<Integer,Value> deepCoppy() throws ContainersException ;

}
