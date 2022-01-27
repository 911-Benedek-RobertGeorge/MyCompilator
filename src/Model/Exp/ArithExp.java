package Model.Exp;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ExpressionEvalException;
import Model.Exceptions.TypeCheckException;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntIValue;
import Model.Value.IValue;

public class ArithExp implements Exp{
    private Exp expresion1;
    private Exp expresion2;
    private int operation;
    private char operationSign;

    public ArithExp(Exp exp1,Exp exp2,int operation){
        expresion1 = exp1;
        expresion2 = exp2;
        this.operation = operation;
    }

    public ArithExp(char c, Exp first, Exp second) throws ExpressionEvalException {
        switch (c){
            case '+':
                operation = 1;
                break;
            case '-':
                operation = 2;
                break;
            case '*':
                operation = 3;
                break;
            case '/':
                operation = 4;
                break;
            default:
                throw new ExpressionEvalException("This operation is unknown.\n");
        }
        operationSign = c;
        expresion1 = first;
        expresion2 = second;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl, MyIHeap<Integer, IValue> heap) throws Exception {
        IValue v1, v2;
        v1= expresion1.eval(tbl,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = expresion2.eval(tbl,heap);
            if (v2.getType().equals(new IntType())) {
                IntIValue i1 = (IntIValue)v1;
                IntIValue i2 = (IntIValue)v2;
                int n1, n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                switch (operation) {
                    case 1:
                        return new IntIValue(n1 + n2);   ///ADD

                    case 2:
                        return new IntIValue(n1 - n2);   ///DIFERENCE
                    case 3:
                        return new IntIValue(n1 * n2);   ///MULTIPLY
                    case 4:                                   ///DIVISION
                        if (n2 == 0) {
                            throw new ExpressionEvalException("division by zero");
                        } else
                            return new IntIValue(n1 / n2);
                    default:
                        throw new ExpressionEvalException("evaluation is not valid");
                }
            }else
                throw new ExpressionEvalException("second operand is not an integer");
        }else
            throw new ExpressionEvalException("first operand is not an integer");

    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typ1, typ2;
        typ1 = expresion1.typecheck(typeEnv);
        typ2 = expresion2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            }
            else throw new TypeCheckException("second operand is not an integer");
        }else throw new TypeCheckException("first operand is not an integer");
    }


    @Override
    public String toString(){
        return expresion1.toString() + " " + operationSign + " " + expresion2.toString();
    }
}
