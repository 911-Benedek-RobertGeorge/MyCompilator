package Model.Type;

import Model.Value.String;
import Model.Value.IValue;

public class StringType implements Type{
    @Override
    public boolean equals(Object another){
        if(another instanceof StringType)  {
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public java.lang.String toString(){
        return "string";
    }
    public IValue defaultValue(){
        return new String("");
    }
}
