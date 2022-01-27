package Model.Containers.OutList;

import Model.Exceptions.ContainersException;

import java.util.List;

public interface MyIList<TValue> {
    public  void remove(int index) throws ContainersException;
    public void add(TValue val);
    public int size();
    public TValue getValueOnIndex(int index) throws ContainersException;
    public MyIList<TValue> deepCoppy();

    List<TValue> getContent();
}
