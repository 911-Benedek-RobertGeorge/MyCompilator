package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.FilesException;
import Model.Exceptions.TypeCheckException;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStmt{
    private Exp expresion;

    public OpenRFile(Exp exp){
        expresion = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String , BufferedReader> fileTable = state.getFileTable();
        IValue IValue = expresion.eval(state.getSymTableStack().peek(),state.getHeap());
        String fileName = (String) IValue.getVal();

        if(IValue.getType().equals(new StringType())) {
            //fileTable.lookup((String)value.getVal());
            if(fileTable != null  && !fileTable.isVarDef(fileName)){
                try {
                    BufferedReader objReader = new BufferedReader(new FileReader(fileName));
                    fileTable.add(fileName,objReader);
                } catch (FileNotFoundException e){
                    throw new FilesException("The file does not exist.\n");
                } catch (IOException e) {
                    throw new FilesException(e.toString());
                }
            }else{
                throw new FilesException("The file already exists in the FileTable.\n");
            }
        }else{
            throw new FilesException("The expresion provided is not a string.\n");
        }
        state.setFileTable(fileTable);
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> type) throws Exception {

        if(!expresion.typecheck(type).equals(new StringType()))
            throw new TypeCheckException("Open File Statement: The file path must be a string");
        return type;

    }
    public String toString(){

        return "OpenFile(" + expresion.toString() + ")";
    }
}
