package Model.ProgramState;

import Model.Containers.Heap.MyHeap;
import Model.Containers.Heap.MyIHeap;

import Model.Containers.Semaphore.MyISemaphore;
import Model.Containers.SymTable.MyDictionary;
import Model.Containers.SymTable.MyIDictionary;
import Model.Containers.OutList.MyIList;
import Model.Containers.ExeStack.MyIStack;
import Model.Exceptions.ContainersException;
import Model.Statement.IStmt;
import Model.Value.IValue;
import org.javatuples.Triplet;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, IValue> symTable;
    private MyIList<IValue> out;
    private IStmt originalProgram;
    private MyIDictionary<java.lang.String, BufferedReader> fileTable;
    private MyIHeap<Integer, IValue> heap;
    private static AtomicInteger id = new AtomicInteger(1);
    private int myId;

    private MyISemaphore<Integer, Triplet<Integer, List<Integer>,Integer>> semaphoreTable;

    public MyISemaphore<Integer, Triplet<Integer, List<Integer>, Integer>> getSemaphoreTable() {
        return semaphoreTable;
    }

    ///Getters and Setters
    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }
    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }
    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }
    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }
    public MyIList<IValue> getOut() {
        return out;
    }
    public void setOut(MyIList<IValue> out) {
        this.out = out;
    }
    public IStmt getOriginalProgram() { return originalProgram; }
    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }
    public void setFileTable(MyIDictionary<String, BufferedReader> fileTable) { this.fileTable = fileTable; }
    public MyIDictionary<String, BufferedReader> getFileTable() { return fileTable; }
    public MyIHeap<Integer, IValue> getHeap() { return heap; }
    public void setHeap( MyIHeap<Integer, IValue> newHeap) { heap = newHeap; }
    public int getMyId() { return myId; }
    public void setMyId(int myId) { this.myId = myId; }
    public static void setId(AtomicInteger atomicInteger) { id = atomicInteger; }
    public static AtomicInteger getId() { return id; }



    public PrgState(@NotNull MyIStack<IStmt> stack, MyIDictionary<String, IValue> symTable, MyIList<IValue> ot, IStmt prg) throws Exception{
        this.exeStack=stack;
        this.symTable = symTable;
        this.out = ot;
        this.fileTable = new MyDictionary<String, BufferedReader>();
        this.heap = new MyHeap<Integer, IValue>();
        myId = id.getAndIncrement();
        stack.push(prg);
    }
    public PrgState(MyIStack<IStmt> stack, MyIDictionary<String, IValue> symTable, MyIList<IValue> ot, MyIDictionary<String, BufferedReader> fileTable, MyIHeap<Integer, IValue> heap, IStmt prg) throws Exception{
        this.exeStack=stack;
        this.symTable = symTable;
        this.out = ot;
        this.fileTable = fileTable;
        this.heap = heap;
        myId = id.getAndIncrement();
        stack.push(prg);
    }

    @Override
    public String toString(){
        String representation ;
        representation = "Id : " + this.myId + "\nExeStack : \n"+
                                this.exeStack.getReversed().toString() + "\n" +
                         "SymTable_" + this.myId + " : \n" +
                                this.symTable.toString() + "\n" +
                         "Out : \n" +
                                this.out.toString() + "\n" +
                         "Heap : \n" +
                                this.getHeap().toString() + "\n" +
                         "FileTable : \n" +
                                this.fileTable.toString();

        representation += "\n";
        return representation;
    }
    ///A5
    public boolean isNotCompleted(){
        if(!exeStack.isEmpty())
        {
            return true;
        }
        return false;
    }
    public PrgState oneStep() throws Exception{

        if(exeStack.isEmpty())
            throw new ContainersException("Program state stack is empty.\n");
        IStmt currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

}