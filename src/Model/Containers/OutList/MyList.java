package Model.Containers.OutList;

import Model.Containers.ExeStack.MyStack;
import Model.Exceptions.ContainersException;

import java.util.ArrayList;
import java.util.List;

public class MyList<TValue> implements MyIList<TValue>{
    List<TValue> list;

    public MyList(){
        list = new ArrayList<TValue>();
    }

    @Override
    public void remove(int index) throws ContainersException {
         list.remove(index);
    }

    @Override
    public void add(TValue val) {
        list.add(val);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public TValue getValueOnIndex(int index)  throws ContainersException {
        return list.get(index);
    }

    @Override
    public String toString(){
        return list.toString();
    }
    public MyIList<TValue> deepCoppy() {
        MyIList<TValue> newList = new MyList<TValue>() ;
        for (TValue elem : this.list)
            newList.add(elem);
        return  newList;
    }

    @Override
    public List<TValue> getContent() {
        return list;
    }
}
