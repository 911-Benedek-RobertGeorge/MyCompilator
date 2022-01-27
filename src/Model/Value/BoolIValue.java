package Model.Value;

import Model.Type.BoolType;

public class BoolIValue implements IValue {
    boolean val;
    public BoolIValue(boolean v){val=v;}
    public Boolean getVal() {return val;}
    @Override
    public java.lang.String toString() {
        return  Boolean.toString(val);
    }
    @Override
    public BoolType getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(IValue object) {
        if(this.getType().equals(object.getType())){
            if(val  == (boolean)object.getVal())
            {
                return true;
            }
        }
        return false;
    }
}
