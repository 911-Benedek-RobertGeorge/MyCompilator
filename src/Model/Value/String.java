package Model.Value;

import Model.Type.StringType;

public class String implements IValue {
    java.lang.String string;
    public String(java.lang.String s) {
        string = s;
    }

    public java.lang.String getVal() {return string;}
    @Override
    public java.lang.String toString() {
        return  "'" + string + "'";
    }
    public StringType getType(){
        return new StringType();
    }

    @Override
    public boolean equals(IValue object) {
        if(this.getType().equals(object.getType())){
            if(string == object.getVal())
            {
                return true;
            }
        }
        return false;
    }

}
