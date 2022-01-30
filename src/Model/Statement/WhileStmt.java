package Model.Statement;

import Model.Containers.ExeStack.MyIStack;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.MyExecutionException;
import Model.Exceptions.TypeCheckException;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.IValue;

public class WhileStmt implements IStmt{

    private Exp expresion;
    private IStmt statement;

    public WhileStmt(Exp expresion, IStmt statement) {
        this.expresion = expresion;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IValue condition = expresion.eval(state.getSymTableStack().peek(),state.getHeap());
        MyIStack stack = state.getExeStack();
        if(condition.getType() instanceof BoolType){
            if((boolean) condition.getVal()){
                stack.push(new WhileStmt(expresion,statement));
                stack.push(statement);
            }
        }else{
            throw new MyExecutionException("The type of " + condition + " is not BoolType\n");
        }
        state.setExeStack(stack);
        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws  Exception {
        Type typexp = expresion.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            statement.typecheck(typeEnv.deepCoppy());
            return typeEnv;
        }
        else throw new TypeCheckException("The condition of While has not the type bool\n");
    }

    public String toString(){
        return "While(" + expresion + "){" + statement + "}";
    }

}
