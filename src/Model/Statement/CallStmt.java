package Model.Statement;

import Model.Containers.ExeStack.MyIStack;
import Model.Containers.Heap.MyIHeap;
import Model.Containers.ProcTable.MyIProcTable;
import Model.Containers.ProcTable.MyProcTable;
import Model.Containers.ProcTable.Pair;
import Model.Containers.SymTable.MyDictionary;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.CallProcedureException;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.IValue;
import com.sun.jdi.Value;

import java.util.List;

public class CallStmt implements IStmt{

    private String name;
    private List<Exp> definitionExp;

    public CallStmt(String name, List<Exp> definition) {
        this.name = name;
        this.definitionExp = definition ;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIProcTable procTable = state.getProcTable();
        Pair<List<String>, IStmt> definitions = procTable.getContent().get(name);
        if( definitions == null)
        {
            throw new CallProcedureException("The procedure name : " + name + " was not found\n");
        }
        MyIDictionary<String,IValue> currentSymTable = state.getSymTableStack().peek();
        MyIDictionary<String, IValue> newDict = currentSymTable.deepCoppy();
        MyIHeap<Integer, IValue> heap = state.getHeap();
        List<String> variableNames = definitions.getFirst();
        if(definitionExp.size() != variableNames.size()){
            throw new CallProcedureException("The number of varible differs ");
        }
        for(int i = 0; i < variableNames.size(); ++i){
            IValue value = definitionExp.get(i).eval(currentSymTable,heap);
            newDict.add(variableNames.get(i),value);
        }
        state.getSymTableStack().push(newDict);
        state.getExeStack().push(new ReturnStmt());
        state.getExeStack().push(definitions.getSecond());

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        return null;
    }
    @Override
    public String toString (){
        return this.name + "(" + this.definitionExp + ")";
    }
}
