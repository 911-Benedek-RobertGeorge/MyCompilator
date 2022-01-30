package Model.Value;

import Model.Type.IntType;

public class IntIValue implements IValue {
    int val;
    public IntIValue(int v){val=v;}
    public Integer getVal() {return val;}
    @Override
    public java.lang.String  toString() {
        return  Integer.toString(val);
    }
    public IntType getType(){
        return new IntType();
    }

    @Override
    public boolean equals(IValue object) {
        if(this.getType().equals(object.getType())){
            if(val  == (int)object.getVal())
            {
                return true;
            }
        }

        return false;
    }

}

