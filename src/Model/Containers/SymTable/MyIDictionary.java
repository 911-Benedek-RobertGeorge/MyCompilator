package Model.Containers.SymTable;

import Model.Exceptions.ContainersException;
import Model.Value.IValue;

import java.util.Map;

public interface MyIDictionary<TKey,TValue>{
    public void add(TKey key,TValue value) throws ContainersException;
    public TValue lookup(String id);
    public int size();
    public TValue remove(TKey key) throws ContainersException;
    public boolean isVarDef(TKey id);
    public IValue getType(TKey var) throws ContainersException;

    public Map<TKey,TValue> getContent();
    public MyIDictionary<TKey,TValue> deepCoppy() throws ContainersException;

}
