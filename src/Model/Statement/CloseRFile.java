package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.FilesException;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.String;
import Model.Value.IValue;

import java.io.BufferedReader;

public class CloseRFile implements IStmt {
    private Exp expresion;

    public CloseRFile(Exp expresion) {
        this.expresion = expresion;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary symTable = state.getSymTable();
        MyIDictionary fileTable = state.getFileTable();
        IValue fileName = expresion.eval(symTable,state.getHeap());
        if(fileName.getType().equals(new StringType())){
            String name = (String) fileName;
            if(fileTable.isVarDef(fileName.getVal()))
            {
                BufferedReader objReader = state.getFileTable().lookup(name.getVal()); //got the reader
                objReader.close();
                fileTable.remove(name.getVal());
            }else
                throw new FilesException("The file with that name does not exist in file table.\n");
        }else
            throw new FilesException("The expresion of statement is not a string.\n");
        state.setFileTable(fileTable);
        return null;
    }
    @Override
    public MyIDictionary<java.lang.String,  Type> typecheck(MyIDictionary<java.lang.String, Type> typeEnv) throws  Exception {
        if(!expresion.typecheck(typeEnv).equals(new StringType()))
            throw new  Exception("The file path must be a string!");
        return typeEnv;
    }


    public java.lang.String toString(){

        return "CloseFile(" + expresion.toString() + ")";
    }
}
