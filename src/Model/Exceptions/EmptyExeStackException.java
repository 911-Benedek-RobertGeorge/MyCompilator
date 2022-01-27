package Model.Exceptions;

public class EmptyExeStackException extends Exception{
    String exception ;
    public EmptyExeStackException(String str){
        exception = str;
    }
    @Override
    public String toString(){
        return exception;
    }
}
