package Model.Containers.LatchTable;

import Model.Exceptions.ContainersException;

import java.util.HashMap;
import java.util.Map;

public class MyLatchTable<Integer,Value> implements MyILatchTable<Integer,Value>{
    private Map<Integer,Value> latchDirecory;
    private int newFreeLocation;

    public MyLatchTable(){
        latchDirecory = new HashMap<Integer, Value>() ;
        newFreeLocation = 1;
    }

    public void setContent(Map<Integer,Value> refreshedHeap){
        latchDirecory = refreshedHeap;
    }
    public Map<Integer,Value>  getContent(){
        return latchDirecory;
    }

    public void add(Integer key, Value value) throws ContainersException {
        latchDirecory.put(key, value);
    }
    public int getFirstFreeLocation(){
        newFreeLocation += 1;
        return newFreeLocation-1;
    }

    @Override
    public Value getValueOnAddress(Integer address) {
        return latchDirecory.get(address);
    }


    @Override
    public String toString(){
        return latchDirecory.toString();
    }
}
