package Model.Statement;

import Model.Containers.ExeStack.MyIStack;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.*;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolIValue;
import Model.Value.IValue;
import Model.Value.IntIValue;

public class IfStmt implements IStmt{
    private Exp expresion;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp exp, IStmt t, IStmt el) {
        expresion=exp;
        thenS=t;
        elseS=el;
    }

    @Override
    public PrgState execute(PrgState state) throws   Exception {
        MyIStack stack = state.getExeStack();

        IValue condition = expresion.eval(state.getSymTableStack().peek(),state.getHeap());
        if (condition.getType().equals(new IntType())){
            if(condition.getVal().equals(new IntIValue(0))){
                stack.push(elseS);
            }else{
                stack.push(thenS);

            }
        }

        if(condition.getType().equals(new BoolType())){
            BoolIValue cond = (BoolIValue) condition;
            if(cond.getVal() == true){
                stack.push(thenS);
            }
            else{
                stack.push(elseS);
            }
        }
        else{
            throw new MyExecutionException("Conditional expresion is not a boolean. ");
        }
        state.setExeStack(stack);
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws  Exception {
        Type typexp = expresion.typecheck(typeEnv);
        if (typexp.equals(new BoolType()) || typexp.equals((new IntType()))) {
            thenS.typecheck(typeEnv.deepCoppy());
            elseS.typecheck(typeEnv.deepCoppy());
            return typeEnv;
        }
        else throw new TypeCheckException("The condition of IF has not the type bool nor int ");
    }


    @Override
    public String toString(){
        return "(IF("+ expresion.toString()+") THEN(" +thenS.toString()  +")ELSE("+elseS.toString()+"))";
    }
}
