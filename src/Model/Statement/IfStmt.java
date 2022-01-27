package Model.Statement;

import Model.Containers.ExeStack.MyIStack;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.*;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolIValue;
import Model.Value.IValue;

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

        IValue condition = expresion.eval(state.getSymTable(),state.getHeap());
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
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.deepCoppy());
            elseS.typecheck(typeEnv.deepCoppy());
            return typeEnv;
        }
        else throw new TypeCheckException("The condition of IF has not the type bool");
    }


    @Override
    public String toString(){
        return "(IF("+ expresion.toString()+") THEN(" +thenS.toString()  +")ELSE("+elseS.toString()+"))";
    }
}
