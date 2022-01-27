package Model.Containers.SymTable;

import Model.Exceptions.ContainersException;
import Model.Value.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<TKey,TValue> implements MyIDictionary<TKey,TValue>{
    private Map<TKey,TValue> dict;

    public MyDictionary (){
        dict = new HashMap <TKey, TValue>() ;
    }

    @Override
    public void add(TKey key, TValue value) throws ContainersException {
        dict.put(key, value);
    }

    @Override
    public TValue lookup(String id) {
       return dict.get(id);
    }

    @Override
    public int size() {
        return dict.size();
    }

    @Override
    public TValue remove(TKey key) throws ContainersException {
        if(dict.get(key) != null)
            return dict.remove(key);
        else
            throw new ContainersException("The key " + key + "is not in the dictionary");
    }

    @Override
    public boolean isVarDef(TKey id) {
        if(dict.get(id) != null){
            return true;
        }
        return false;

    }

    @Override
    public IValue getType(TKey var) throws ContainersException {
        if(dict.get(var) == null){
            throw new ContainersException("The variable " + var + " is not declared yet.");
        }
         TValue value = dict.get(var);

            return  null ; //(Value) value.getType();

    }

    public Map<TKey,TValue>  getContent(){
        return dict;
    }

    @Override
    public MyIDictionary<TKey, TValue> deepCoppy() throws ContainersException {
        MyIDictionary newDict = new MyDictionary<TKey,TValue>();
        for(TKey key : this.dict.keySet()){
            newDict.add(key,dict.get(key));
        }
        return newDict;
    }


    @Override
    public String toString(){
        return dict.toString();
    }
}
