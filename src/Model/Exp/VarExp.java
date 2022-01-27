package Model.Exp;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ExpressionEvalException;
import Model.Type.Type;
import Model.Value.IValue;

public class VarExp implements Exp{
    String id;


    public VarExp(String id){
        this.id = id;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, MyIHeap<Integer, IValue> heap) throws ExpressionEvalException {
        IValue IValue;
        IValue =tbl.lookup(id);
        if ( IValue != null){
             return IValue;
         }
        else{
             throw new ExpressionEvalException("Key " + id + " was not found.");
        }
    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws Exception{
        return typeEnv.lookup(id);
    }

    @Override
    public String toString(){

            return id  ;

    }
}
