package Model.Statement;

import Model.Containers.ExeStack.MyIStack;
import Model.Containers.ExeStack.MyStack;
import Model.Containers.Heap.MyIHeap;
import Model.Containers.OutList.MyIList;
import Model.Containers.SymTable.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Type.Type;

public class ForkStmt implements IStmt{

    private IStmt statement;

    public ForkStmt(IStmt statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack newStack =  new MyStack<IStmt>();

        MyIDictionary newSymTable = state.getSymTable().deepCoppy();
        MyIList newOutput = state.getOut();
        MyIHeap newHeap = state.getHeap();
        MyIDictionary newFileTable = state.getFileTable();



        PrgState newProgram = new PrgState(newStack,newSymTable,newOutput,newFileTable,newHeap,statement);

        return newProgram;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws  Exception {
            statement.typecheck(typeEnv.deepCoppy());
            return typeEnv;
    }

    public String toString (){
        return "Fork("+ statement.toString() + ")";
    }
}
