package Model.Containers.Heap;

import Model.Containers.ExeStack.MyStack;
import Model.Containers.SymTable.MyDictionary;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ContainersException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<Integer,Value> implements MyIHeap<Integer,Value>{
    private Map<Integer,Value> heapDictionary;
    private int newFreeLocation;

    public MyHeap(){
        heapDictionary = new HashMap<Integer, Value>() ;
        newFreeLocation = 1;
    }

    public void setContent(Map<Integer,Value> refreshedHeap){
        heapDictionary = refreshedHeap;
    }
    public Map<Integer,Value>  getContent(){
        return heapDictionary;
    }

    public void add(Integer key, Value value) throws ContainersException {
        heapDictionary.put(key, value);
    }
    public int getFirstFreeLocation(){
        newFreeLocation += 1;
        return newFreeLocation-1;

    }

    @Override
    public Value getValueOnAddress(Integer address) {
        return heapDictionary.get(address);
    }

    @Override
    public MyIHeap<Integer, Value> deepCoppy() throws ContainersException {

        MyIHeap newDict = new MyHeap<Integer,Value>();
        for(Integer key : this.heapDictionary.keySet()){
            newDict.add(key,heapDictionary.get(key));
        }
        return newDict;
    }

    @Override
    public String toString(){
        return heapDictionary.toString();
    }


}
