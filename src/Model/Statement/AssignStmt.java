package Model.Statement;

import Model.Containers.ExeStack.MyIStack;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.*;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.IValue;

public class AssignStmt implements IStmt{
    String id;
    Exp expresion;

    public AssignStmt(String name, Exp exp){
        id = name;
        expresion = exp;
    }
    @Override
    public String toString(){
        return id + "=" + expresion.toString();    /// ii ok asa cu expresion to string ? sau tre eval (expresion).toString()
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> stack= state.getExeStack();
        MyIDictionary<String , IValue> symTable = state.getSymTable();

        if(symTable.isVarDef(id))
        {
            IValue IValue = expresion.eval(symTable, state.getHeap());
            if(IValue.getType().equals(symTable.lookup(id).getType())){
                symTable.add(id, IValue);
            }
            else{
                throw new MyExecutionException("Type of expression and type of variable do not match");
            }
        }
        else{
            throw new MyExecutionException("Variable "+ id + " is not declared");
        }

        state.setExeStack(stack);
        state.setSymTable(symTable);
        return null;
    }
    public MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typeVar = typeEnv.lookup(id);
        Type typExp = expresion.typecheck(typeEnv);
        if (typeVar.equals(typExp)) return typeEnv;
        else throw new TypeCheckException("Assignment: right hand side and left hand side have different types ");
    }
}
