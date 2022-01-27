package Model.Statement;

import Model.Containers.ExeStack.MyIStack;
import Model.Containers.SymTable.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.Type;

public class CompStmt implements IStmt{
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt one,IStmt two){
        this.first = one;
        this.second = two;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stack= state.getExeStack();
        stack.push(second);
        stack.push(first);
        state.setExeStack(stack);
        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        //MyIDictionary<String, Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String, Type> typEnv2 = second.typecheck(typEnv1);
        //return typEnv2;
        return second.typecheck(first.typecheck(typeEnv));
    }


    public String toString() {
        return "("+first.toString() + ";" + second.toString()+")";
    }
}
