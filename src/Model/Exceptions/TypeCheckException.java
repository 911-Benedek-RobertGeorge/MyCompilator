package Model.Exceptions;

public class TypeCheckException extends Exception{
    String exception ;
    public TypeCheckException(String str){
        exception = str;
    }
    @Override
    public String toString(){
        return exception;
    }
}
