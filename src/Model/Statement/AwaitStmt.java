package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.MyExecutionException;
import Model.Exceptions.TypeCheckException;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IValue;
import Model.Value.IntIValue;

public class AwaitStmt implements IStmt{

    private String variableName;

    public AwaitStmt(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {

        IValue result = state.getSymTable().lookup(variableName);
        if(result != null && result.getType().equals(new IntType()))
        {
            int foundIndex = ((IntIValue)result).getVal();
            Integer cv = state.getLatchTable().getValueOnAddress(foundIndex);
            if (cv == null){
                throw new MyExecutionException("AWAIT : the latchtable dost not have such an index " + foundIndex);
            }else{
                if(cv == 0){
                    return null;
                }
                else{
                    state.getExeStack().push(this);
                }
            }

        }else
        {
            throw new MyExecutionException("AWAIT : var wasnt found or dont have Int Type");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {

        Type typeVar = typeEnv.lookup(variableName);
        if (typeVar != null && typeVar.equals(new IntType()))
        {
            return typeEnv;
        }
        else{
            throw new TypeCheckException("count down : the var type is not int");
        }
    }

    @Override
    public String toString() {
        return "Await(" +  variableName + ")";

    }
}
