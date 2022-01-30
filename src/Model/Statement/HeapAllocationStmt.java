package Model.Statement;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.MyExecutionException;
import Model.Exceptions.TypeCheckException;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefIValue;
import Model.Value.IValue;

public class HeapAllocationStmt implements IStmt{
    String variableName;
    Exp expresion;


    public HeapAllocationStmt(String var_name , Exp exp ){
        variableName = var_name;
        expresion = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String,IValue> symTable = state.getSymTableStack().peek();
        MyIHeap<Integer, IValue> heap = state.getHeap();
        IValue variable = symTable.lookup(variableName);


        if(variable != null) {  /// returns null if not found
            if (variable.getType() instanceof RefType) {
                IValue expIValue = this.expresion.eval(symTable,heap);
                RefIValue refValue = (RefIValue) variable;
                Type innerType = ((RefType) refValue.getType()).getInner();

                if(expIValue.getType().equals(innerType)){
                    int coppyAddress  = heap.getFirstFreeLocation();
                    heap.add(coppyAddress, expIValue);
                    symTable.add(this.variableName, new RefIValue(coppyAddress,innerType)); // works as update
                }else
                    throw new MyExecutionException("There exist a mismatch beetween the expresion's type " + expIValue.getType() +
                            " and the inner type of the reference value " + innerType);
            }else
                throw new MyExecutionException("The type of the " + this.variableName + " is not RefType.\n");
        }else
            throw new MyExecutionException(this.variableName +" was not found in the smTable\n");
        state.setHeap(heap);
        state.setSymTableStack(symTable);
        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws  Exception {
        Type typevar = typeEnv.lookup(variableName);
        Type typexp = expresion.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp))) return typeEnv;
        else throw new TypeCheckException("Heap allocation(NEW) stmt: right hand side and left hand side have different types ");
    }
    @Override
    public String toString(){
        return "HeapAllocation(" + variableName + "," + expresion + ")";
    }
}
