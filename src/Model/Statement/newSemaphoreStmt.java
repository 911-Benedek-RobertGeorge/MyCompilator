package Model.Statement;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.Semaphore.MyISemaphore;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.MyExecutionException;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IValue;
import Model.Value.IntIValue;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class newSemaphoreStmt implements IStmt{

    private static Lock lock = new ReentrantLock();
    private  static int  newFreeLocation = -1;
    private String var;
    private Exp exp1;
    private Exp exp2;

    public newSemaphoreStmt(String var, Exp exp1, Exp exp2) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {


        MyIDictionary<String,IValue> symTable = state.getSymTable();
        MyIHeap<Integer, IValue> heapTable = state.getHeap();
        IValue val1 = exp1.eval(symTable,heapTable);
        IValue val2 = exp2.eval(symTable,heapTable);
        if(val1.getType().equals(val2.getType()) && val1.getType().equals(new IntType()))
        {
            lock.lock();
                newFreeLocation ++;
                MyISemaphore<Integer, Triplet<Integer, List<Integer>, Integer>> semaphoreTable = state.getSemaphoreTable();
                semaphoreTable.getContent().put(newFreeLocation,
                        new Triplet<Integer, List<Integer>, Integer>((Integer) val1.getVal(),new ArrayList<>(),(Integer) val2.getVal()));
                IValue variable = symTable.lookup(var);
                if (variable != null && variable.getType().equals(new IntType()))
                {
                    symTable.add(var,new IntIValue(newFreeLocation));
                }else{
                    throw new MyExecutionException(" SEMAPHORE : The variable is not found in the symtable its not int type");
                }
            lock.unlock();
        }else {
            throw new MyExecutionException("THe types of expresions are not int");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "Semaphore(" + var +","+ exp1 + "," + exp2 + ")";
    }
}
