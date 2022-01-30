package Model.Exp;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ExpressionEvalException;
import Model.Exceptions.TypeCheckException;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefIValue;
import Model.Value.IValue;

public class ReadHeapExp implements Exp{
    private Exp expresion ;

    public ReadHeapExp(Exp expresion){
        this.expresion = expresion;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, MyIHeap<Integer, IValue> heap) throws Exception {
        IValue expIValue = expresion.eval(tbl,heap);
       // System.out.println(expIValue.getType());
        if (expIValue.getType() instanceof RefType) {
            RefIValue valueReferenced = (RefIValue) expIValue;
            int address = valueReferenced.getAddr();
            IValue IValueAtAddress = heap.getValueOnAddress(address);
            if (IValueAtAddress == null) // means the address was not found in the heap
            {
                throw new ExpressionEvalException("The address " + address + " was not found in the heap\n");
            }
            return IValueAtAddress;
        }else
            throw new ExpressionEvalException("The value " + expIValue.toString() + " is not a RefValue\n");

    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typ = expresion.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft = (RefType) typ;
            return reft.getInner();
        } else throw new TypeCheckException("the rH argument is not a Ref Type");
    }

    @Override
    public String toString(){
        return "readH(" + expresion + ")";
    }
}
