package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefIValue implements IValue {
    private int address;
    private Type locationType;

    public RefIValue(int adr, Type referenced){
        locationType = referenced;
        address = adr;
    }

    public int getAddr() {
        return address;
    }

    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public boolean equals(IValue another) {

        return false;
    }

    @Override
    public Type getVal() {
        return locationType;
    }
    @Override
    public java.lang.String toString() {
        return  "(" + address + "," + locationType.toString() +")";
    }
}
