package Model.Exp;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ExpressionEvalException;
import Model.Exceptions.TypeCheckException;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolIValue;
import Model.Value.IValue;

public class LogicExp implements Exp{
    Exp expresion1;
    Exp expresion2;
    int operation;

    public LogicExp(Exp exp1,Exp exp2,int operation){
        this.expresion1 = exp1;
        this.expresion2 = exp2;
        this.operation = operation;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, MyIHeap<Integer, IValue> heap) throws Exception {
        IValue operand1,
                operand2;
        operand1 = expresion1.eval(tbl,heap);
        if(operand1.getType().equals(new BoolType())) {
            operand2 = expresion2.eval(tbl,heap);
            if(operand2.getType().equals(new BoolType())){
                switch(operation) {
                    case 1: ///AND
                        if(operand1.equals(new BoolIValue(true)) && operand2.equals(new BoolIValue(true))){
                            return new BoolIValue(true);
                        }
                        else {
                            return new BoolIValue(false);
                        }
                    case 2: /// OR
                        if(operand1.equals(new BoolIValue(false)) && operand2.equals(new BoolIValue(false))){
                            return new BoolIValue(false);
                        }
                        else {
                            return new BoolIValue(true);
                        }
                    default:
                        throw new ExpressionEvalException("this evaluation is not valid");

                }
            }else throw new ExpressionEvalException("second operand is not a bool");
        }else throw new ExpressionEvalException("first operand is not bool");

    }

    @Override
    public String toString(){
        if(operation == 1)
            return expresion1.toString() + " " +  "AND" + " " + expresion2.toString();
        else
            return expresion1.toString() + " " +  "OR" + " " + expresion2.toString();

    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type operand1 = expresion1.typecheck(typeEnv);
        Type operand2 = expresion2.typecheck(typeEnv);
        if(operand1.equals(new BoolType())) {

            if(operand2.equals(new BoolType())) {
                return new BoolType();
            }
            else{
                throw new TypeCheckException("second operand is not bool type\n");
            }
        }else {
            throw new TypeCheckException("first operand is not bool type\n");
        }
    }
}
