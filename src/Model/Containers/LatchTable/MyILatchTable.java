package Model.Containers.LatchTable;

import Model.Exceptions.ContainersException;

import java.util.Map;

public interface MyILatchTable<Integer,Value> {
    public void add(Integer key, Value value) throws ContainersException;
    public int getFirstFreeLocation();
    public void setContent(Map<Integer,Value> refreshedHeap);
    public Map<Integer,Value>  getContent();
    Value getValueOnAddress(Integer address);

}
