package Model.Statement;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.Semaphore.MyISemaphore;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.MyExecutionException;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IValue;
import org.javatuples.Triplet;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class ReleseStmt implements IStmt{
    private static Lock lock = new ReentrantLock();

    private String var;

    public ReleseStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        MyIHeap<Integer, IValue> heapTable = state.getHeap();
        MyISemaphore<Integer, Triplet<Integer, List<Integer>, Integer>> semaphoreTable = state.getSemaphoreTable();
        IValue result = symTable.lookup(var);
        if(result != null && result.getType().equals(new IntType())){
            int foundIndex = (Integer) result.getVal();
            lock.lock();
            Triplet<Integer, List<Integer>, Integer> triplet = semaphoreTable.getValueOnAddress(foundIndex);
            if(triplet == null){
                throw new MyExecutionException("Index not in semaphore tabel");
            }else{

                List<Integer> prgrams = triplet.getValue1();
                    if(prgrams.contains(state.getMyId())){
                         prgrams.remove(state.getMyId());
                    }

            }
            lock.unlock();


        }else {
            throw new MyExecutionException("not required type");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        return null;
    }
}
