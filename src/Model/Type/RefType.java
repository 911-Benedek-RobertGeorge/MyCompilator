package Model.Type;

import Model.Value.RefIValue;
import Model.Value.IValue;

public class RefType implements Type {
        private Type inner;

        public RefType(Type inner) {
            this.inner = inner;
        }

    public RefType() {

    }

    public Type getInner() {
            return inner;
        }

        public boolean equals(Object another) {

            if (another instanceof RefType){
                 return inner.equals(((RefType) another).getInner());
            }
            else return false;
        }

        public String toString() {
            return "Ref(" + inner.toString() + ")";
        }

        public IValue defaultValue() {
            return new RefIValue(0, inner);
        }
    }

