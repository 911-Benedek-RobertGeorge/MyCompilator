package Model.Containers.Semaphore;

import Model.Exceptions.ContainersException;
import org.javatuples.Triplet;

import java.util.HashMap;
import java.util.Map;

public class MySemaphore<Address,Value> implements MyISemaphore<Address, Value>{
    private Map<Address,Value> semaphoreDictionary;
    private int newFreeLocation;

    public MySemaphore(){
        semaphoreDictionary = new HashMap<Address, Value>() ;
        newFreeLocation = 1;

    }
    @Override
    public void setContent(Map<Address, Value> refreshedHeap){
        semaphoreDictionary = refreshedHeap;
    }
    @Override
    public Map<Address,Value>  getContent(){
        return semaphoreDictionary;
    }
    @Override
    public void add(Address key, Value value) throws ContainersException {
        semaphoreDictionary.put(key, value);
    }
    @Override
    public int getFirstFreeLocation(){
        newFreeLocation += 1;
        return newFreeLocation-1;

    }

    @Override
    public Value getValueOnAddress(Address address) {
        return semaphoreDictionary.get(address);
    }



    @Override
    public String toString(){
        return semaphoreDictionary.toString();
    }

}
