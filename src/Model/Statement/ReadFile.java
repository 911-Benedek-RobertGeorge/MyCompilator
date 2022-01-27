package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.FilesException;
import Model.Exceptions.TypeCheckException;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntIValue;
import Model.Value.String;
import Model.Value.IValue;

import java.io.BufferedReader;

public class ReadFile implements IStmt{
    private Exp expresion;
    private java.lang.String variableName;

    public ReadFile(Exp expresion, java.lang.String variableName) {
        this.expresion = expresion;
        this.variableName = variableName;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary symTable = state.getSymTable();
        IValue variable = (IValue) symTable.lookup(variableName);

        if(variable != null) {
            if (variable.getType().equals(new IntType()))
            {

                IValue fileName = expresion.eval(symTable, state.getHeap());
                if(fileName.getType().equals(new StringType())){ ///if its a stringValue
                    String name = (String)fileName;
                    BufferedReader objReader = state.getFileTable().lookup(name.getVal()); //got the reader
                    if(objReader != null) ///we found the file in the fileTable
                    {
                        java.lang.String newLine = objReader.readLine();

                        Integer newInteger;
                        if(newLine == null)
                            newInteger = 0;
                        else
                            newInteger = Integer.parseInt(newLine);
                        IntIValue newValue = new IntIValue(newInteger);
                        symTable.add(variableName,newValue);
                    }else{
                        throw new FilesException("The file was not found in the FileTable\n");
                    }
                }
                else {
                    throw new FilesException("The expresion in ReadFile is not a string type .\n");
                }
            }else{
                throw new FilesException("FileRead: the varialbe " + variable.toString() + " is not an int type.\n");
            }
        }else{
            throw new FilesException("FileRead: the variable " + variable.toString() + "is not declared\n");
        }
        state.setSymTable(symTable);
        return null;
    }


    @Override
    public MyIDictionary<java.lang.String, Type> typecheck(MyIDictionary<java.lang.String, Type> type)throws Exception{
        Type typeVariable = type.lookup(variableName);
        Type typeExpression = expresion.typecheck(type);
        if(!typeVariable.equals(new IntType()))
            throw new TypeCheckException("Read File Statement: the type of the variable must be an int");
        if(!typeExpression.equals(new StringType()))
            throw new TypeCheckException("Read File Statement: the type of the expression must be a string");
        return type;
    }

    public java.lang.String toString(){

        return "ReadFile(" + expresion.toString() + ")";
    }
}
