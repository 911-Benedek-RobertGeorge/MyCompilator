package Model.Exp;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ExpressionEvalException;
import Model.Exceptions.TypeCheckException;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolIValue;
import Model.Value.IValue;

public class RelationalExp implements Exp{
    Exp expresion1,expresion2;
    String relation;

    public RelationalExp(String rel,Exp expresion1, Exp expresion2) {
        this.expresion1 = expresion1;
        this.expresion2 = expresion2;
        relation = rel;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, MyIHeap<Integer, IValue> heap) throws Exception {
        IValue IValue1 = expresion1.eval(tbl,heap);
        IValue IValue2 = expresion2.eval(tbl,heap);
        if(IValue1.getType().equals(IValue2.getType()) && IValue1.getType().equals(new IntType())) ///daca ambele sunt int type
        {
            BoolIValue result = new BoolIValue(false);
            if (relation == "<")
            {
                result = new BoolIValue((Integer) IValue1.getVal() < (Integer) IValue2.getVal());
            }
            if (relation == "<=")
            {
                result = new BoolIValue((Integer) IValue1.getVal() <= (Integer) IValue2.getVal());
            }
            if (relation == "==")
            {
                result = new BoolIValue((Integer) IValue1.getVal() == (Integer) IValue2.getVal());
            }
            if (relation == "!=")
            {
                result = new BoolIValue((Integer) IValue1.getVal() != (Integer) IValue2.getVal());
            }
            if (relation == ">")
            {
                result = new BoolIValue((Integer) IValue1.getVal() > (Integer) IValue2.getVal());
            }
            if (relation == ">=")
            {
                result = new BoolIValue((Integer) IValue1.getVal() >= (Integer) IValue2.getVal());
            }
            return result;
        }else
            throw new ExpressionEvalException("The expresions don't have the int type\n");

    }
    public String toString(){
        return expresion1.toString() +" "+ relation + " " +expresion2.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type value1 = expresion1.typecheck(typeEnv);
        Type value2 = expresion2.typecheck(typeEnv);
            if(value1.equals(value2)) {
                if(value1.equals(new IntType())){
                    return new BoolType();
                }else
                {
                    throw new TypeCheckException("the second operand is not int Type\n");
                }
        }else{
                throw new TypeCheckException("first operand is not int Type\n");
            }

    }
}
