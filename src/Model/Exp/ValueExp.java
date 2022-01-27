package Model.Exp;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ExpressionEvalException;
import Model.Type.Type;
import Model.Value.IValue;

public class ValueExp implements Exp{
    private IValue IValue;

    public ValueExp(IValue IValue){
        this.IValue = IValue;
    }
    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, MyIHeap<Integer, IValue> heap) throws ExpressionEvalException {
        return IValue;
    }
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws Exception{
        return IValue.getType();
    }

    @Override
    public String toString(){
        return IValue.toString();
    }
}
