package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ContainersException;
import Model.Exceptions.MyExecutionException;
import Model.ProgramState.PrgState;
import Model.Type.*;
import Model.Value.RefIValue;

public class VarDeclStmt implements IStmt{

    private String name;
    private Type type;


    public VarDeclStmt(String name,Type type){
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyExecutionException, ContainersException {

        MyIDictionary symTable = state.getSymTableStack().peek();


        if (symTable.isVarDef(name))
        {
            throw new MyExecutionException("Variable is already declared.");
        }else{
            if(type.toString() == "int")
                symTable.add(name,new IntType().defaultValue());
            else
                if(type.toString() == "bool")
                    symTable.add(name, new BoolType().defaultValue());
                else
                    if(type.toString() == "string")
                        symTable.add(name,new StringType().defaultValue());
                    else
                        if(type instanceof RefType)
                            symTable.add(name,new RefIValue(0,((RefType) type).getInner()));
        }

        state.setSymTableStack(symTable);
        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws Exception {
        typeEnv.add(name, type);
        return typeEnv;
    }

    @Override
     public String toString(){
        return   type.toString() + " " + name ;
    }
}
