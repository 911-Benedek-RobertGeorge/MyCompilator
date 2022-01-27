package Model.Value;


import Model.Type.Type;

public interface IValue {
    public Type getType();
    public boolean equals(IValue object);
    public Object getVal();
    public java.lang.String toString();
}
