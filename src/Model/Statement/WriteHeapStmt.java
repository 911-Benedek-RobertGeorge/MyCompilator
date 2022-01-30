package Model.Statement;


import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.ExpressionEvalException;
import Model.Exceptions.TypeCheckException;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefIValue;
import Model.Value.IValue;

public class WriteHeapStmt implements IStmt {
    private String variableName;
    private Exp expresion;

    public WriteHeapStmt(String varName, Exp expresion) {
        this.variableName = varName;
        this.expresion = expresion;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {

        IValue variable = state.getSymTableStack().peek().lookup(variableName);
        MyIHeap<Integer, IValue> heap = state.getHeap();

        if (variable != null && variable.getType() instanceof RefType) { // if its null it was not found
            RefIValue refValue = (RefIValue) variable;
            int address = refValue.getAddr();
            Type locationType = refValue.getVal();
            IValue IValueOnAddress = heap.getValueOnAddress(address);
            if (IValueOnAddress == null) {
                throw new ExpressionEvalException("The variable " + variableName + " is not defined in heap \n");
            }
            IValue evaluationIValue = expresion.eval(state.getSymTableStack().peek(), heap);
            if (evaluationIValue.getType().equals(locationType) ){
                heap.add(address, evaluationIValue);
            } else {
                throw new ExpressionEvalException("There is a mismatch between the tipes of " + evaluationIValue + " and " + variableName);
            }


        } else {
            throw new ExpressionEvalException("The variable " + variableName + " was not found or its type is not RefType\n");
        }
    state.setHeap(heap);
    return null;
    }


    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type typevar = typeEnv.lookup(variableName);
        Type typexp = expresion.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new TypeCheckException("Write stmt: right hand side and left hand side have different types ");
    }
    @Override
    public String toString(){
        return "WriteH(" + variableName + "," + expresion + ")";
    }
}

