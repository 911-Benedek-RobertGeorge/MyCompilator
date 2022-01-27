package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.Type;


public interface  IStmt {
    public PrgState execute(PrgState state) throws Exception;
    //which is the execution method for a statement.
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws Exception;
}