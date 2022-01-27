package Model.Exceptions;

public class ExpressionEvalException extends Exception{
    String exception ;
    public ExpressionEvalException(String str){
        exception = str;
    }
    @Override
    public String toString(){
        return exception;
    }
}
