package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.MyExecutionException;
import Model.Exceptions.TypeCheckException;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IValue;
import Model.Value.IntIValue;

import java.util.concurrent.ExecutionException;

public class newLatchStmt implements IStmt{

    private static int newFreeLocation = -1;
    private String variableName ;
    private Exp expresion;

    public newLatchStmt(String variableName, Exp expresion) {
        this.variableName = variableName;
        this.expresion = expresion;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {


        MyIDictionary<String ,IValue> symTable = state.getSymTable();
        IValue value = expresion.eval(symTable,state.getHeap());
        if (value.getType().equals(new IntType())){

            synchronized (state.getLatchTable()) {
                ++newFreeLocation;
                state.getLatchTable().add(newFreeLocation, ((IntIValue)value).getVal());
             }

            IValue variableValue = symTable.lookup(variableName);
            if (variableValue.getType().equals(new IntType())){
                symTable.add(variableName,new IntIValue(newFreeLocation));
            }else {
                throw new MyExecutionException("New LATCH : the Variable does not exists in the symTable or its not an integer");
            }

        }else
        {
            throw new MyExecutionException("NEW LATCH : the type of expresion is not int");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeVar = typeEnv.lookup(variableName);
        Type typExp = expresion.typecheck(typeEnv);
        if (!typExp.equals(new IntType()))
        {
            throw new TypeCheckException("count down : the expresion type is not int");
        }
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
        return "newLatch(" +  variableName + " , " + expresion + ")";

    }
}
