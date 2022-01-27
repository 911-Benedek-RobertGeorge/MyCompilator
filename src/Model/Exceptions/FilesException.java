package Model.Exceptions;

public class FilesException  extends Exception{
    String exception ;
    public FilesException(String str){
        exception = str;
    }
    @Override
    public String toString(){
        return exception;
    }
}

