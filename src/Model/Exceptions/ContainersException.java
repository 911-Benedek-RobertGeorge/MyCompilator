package Model.Exceptions;

public class ContainersException extends Exception{
    String exception ;
    public ContainersException(String str){
        exception = str;
    }
    @Override
    public String toString(){
        return exception;
    }
}
