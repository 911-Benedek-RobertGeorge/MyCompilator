package Model.Statement;

import Model.Containers.ExeStack.MyIStack;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ContainersException;
import Model.Exceptions.EmptyExeStackException;
import Model.Exceptions.ExpressionEvalException;
import Model.ProgramState.PrgState;
import Model.Type.Type;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws EmptyExeStackException, ExpressionEvalException, ContainersException {
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> type) throws Exception {
        return type;
    }
}
