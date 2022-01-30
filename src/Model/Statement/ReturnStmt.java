package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.Type;

public class ReturnStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws Exception {
        state.getSymTableStack().pop();
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "Return()";
    }
}
