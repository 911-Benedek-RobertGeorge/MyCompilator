package Model.Type;

import Model.Value.IntIValue;
import Model.Value.IValue;

public class IntType implements Type{
     @Override
    public boolean equals(Object another){
        if(another instanceof IntType)  {
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public String toString(){
         return "int";
    }
   public IValue defaultValue(){
         return new IntIValue(0);
    }
}
