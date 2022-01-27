package Model.Exp;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Type.Type;
import Model.Value.IValue;

public interface Exp {
    IValue eval(MyIDictionary<String, IValue> tbl, MyIHeap<Integer, IValue> heap) throws Exception;
    public String toString();
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws Exception;
}
