package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.MyExecutionException;
import Model.Exceptions.TypeCheckException;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IValue;
import Model.Value.IntIValue;

public class CountDown implements IStmt{

    private String variableName;

    public CountDown(String variableName) {
        this.variableName = variableName;
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        IValue result = state.getSymTable().lookup(variableName);
        if(result != null && result.getType().equals(new IntType()))
        {
            int foundIndex = ((IntIValue)result).getVal();
             System.out.println(state.getLatchTable());
             Integer cv = state.getLatchTable().getValueOnAddress(foundIndex);
            if (cv == null){
                throw new MyExecutionException("COUNTDOWN : the latchtable dost not have such an index " + foundIndex);
            }else{
                if(cv > 0){
                    synchronized (state.getLatchTable()) {
                        state.getLatchTable().add(foundIndex, (int) (cv - 1));
                        state.getOut().add(new IntIValue(state.getMyId()));
                    }
                }
                else{
                    state.getOut().add(new IntIValue(state.getMyId()));

                }
            }

        }else
        {
            throw new MyExecutionException("countDOWN : var wasnt found or dont have Int Type");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeVar = typeEnv.lookup(variableName);
        if (typeVar.equals(new IntType()))
        {
            return typeEnv;
        }
        else{
            throw new TypeCheckException("count down : the var type is not int");
        }

    }

    @Override
    public String toString() {
        return "CountDown(" +  variableName + ")";

    }
}
