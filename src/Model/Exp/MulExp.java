package Model.Exp;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ExpressionEvalException;
import Model.Exceptions.TypeCheckException;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IValue;
import Model.Value.IntIValue;

public class MulExp implements Exp {
    private Exp exp1,exp2;

    public MulExp(Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }


    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, MyIHeap<Integer, IValue> heap) throws Exception {
        IValue v1, v2;
        IValue result = null;
        v1= exp1.eval(tbl,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = exp2.eval(tbl,heap);
            if (v2.getType().equals(new IntType())) {
                int val1 = ((IntIValue)v1).getVal();
                int val2 = ((IntIValue)v2).getVal();

                 result = new IntIValue((val1*val2) - (val1 + val2));
            }else
                throw new ExpressionEvalException("second operand is not an integer");
        }else
            throw new ExpressionEvalException("first operand is not an integer");


        return result;

    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typ1, typ2;
        typ1 = exp1.typecheck(typeEnv);
        typ2 = exp2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            }
            else throw new TypeCheckException("second operand is not an integer");
        }else throw new TypeCheckException("first operand is not an integer");
    }

    @Override
    public String toString(){
        return "MUL(" + exp1.toString() + " , " + exp2.toString() + ")";
    }

}
