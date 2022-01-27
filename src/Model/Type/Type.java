package Model.Type;

import Model.Value.IValue;

public interface Type {
    boolean equals(Object another);
    String toString();
    IValue defaultValue();
 }
