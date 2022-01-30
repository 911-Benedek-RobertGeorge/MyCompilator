package Model.ProgramState;

import Model.Containers.Heap.MyHeap;
import Model.Containers.Heap.MyIHeap;
import Model.Containers.ProcTable.MyIProcTable;
import Model.Containers.ProcTable.MyProcTable;
import Model.Containers.ProcTable.Pair;
import Model.Containers.SymTable.MyDictionary;
import Model.Containers.SymTable.MyIDictionary;
import Model.Containers.OutList.MyIList;
import Model.Containers.ExeStack.MyIStack;
import Model.Exceptions.ContainersException;
import Model.Statement.IStmt;
import Model.Value.IValue;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private Stack<MyIDictionary<String, IValue> > symTableStack = new Stack<>();
    private MyIList<IValue> out;
    private IStmt originalProgram;
    private MyIDictionary<java.lang.String, BufferedReader> fileTable;
    private MyIHeap<Integer, IValue> heap;
    private static AtomicInteger id = new AtomicInteger(1);
    private int myId;
    private MyIProcTable procTable;


    ///Getters and Setters
    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }
    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }
    public  Stack<MyIDictionary<String, IValue> > getSymTableStack() {
        return symTableStack;
    }
    public void setSymTableStack(MyIDictionary<String, IValue> symTable) {
        if(!symTableStack.isEmpty())
        {
            symTableStack.pop();
        }
        this.symTableStack.push(symTable);
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
        this.symTableStack.push(symTable);
        this.out = ot;
        this.fileTable = new MyDictionary<String, BufferedReader>();
        this.heap = new MyHeap<Integer, IValue>();
        this.procTable = new MyProcTable();

        myId = id.getAndIncrement();
        stack.push(prg);
    }
    public PrgState(MyIStack<IStmt> stack, MyIDictionary<String, IValue> symTable, MyIList<IValue> ot, MyIDictionary<String, BufferedReader> fileTable, MyIHeap<Integer, IValue> heap, IStmt prg) throws Exception{
        this.exeStack=stack;
        this.symTableStack.push(symTable);
        this.out = ot;
        this.fileTable = fileTable;
        this.heap = heap;
        this.procTable = new MyProcTable();

        myId = id.getAndIncrement();
        stack.push(prg);
    }

    public PrgState(MyIStack<IStmt> stack, Stack<MyIDictionary<String, IValue>> symTableStack, MyIList<IValue> ot, MyIDictionary<String, BufferedReader> fileTable, MyIHeap<Integer, IValue> heap, IStmt prg) throws Exception{
        this.exeStack=stack;
        this.symTableStack = symTableStack;
        this.out = ot;
        this.fileTable = fileTable;
        this.heap = heap;
        this.procTable = new MyProcTable();
        myId = id.getAndIncrement();
        stack.push(prg);
    }
    public PrgState(MyIStack<IStmt> stack,  MyIDictionary<String, IValue>  symTableStack, MyIList<IValue> ot,MyIProcTable procTable ,IStmt prg) throws Exception{
        this.exeStack=stack;
        this.symTableStack.push( symTableStack);
        this.out = ot;
        this.fileTable = new MyDictionary<String, BufferedReader>();
        this.heap = new MyHeap<Integer, IValue>();
        this.procTable = procTable;
        myId = id.getAndIncrement();
        stack.push(prg);
    }
    public PrgState(MyIStack newStack, Stack<MyIDictionary<String, IValue>> newSymTableStack, MyIList newOutput, MyIDictionary newFileTable, MyIHeap newHeap, MyIProcTable procTable, IStmt statement) {
        this.exeStack=newStack;
        this.symTableStack = newSymTableStack;
        this.out = newOutput;
        this.fileTable = newFileTable;
        this.heap = newHeap;
        this.procTable = procTable;
        myId = id.getAndIncrement();
        exeStack.push(statement);
    }

    @Override
    public String toString(){
        String representation ;
        representation = "Id : " + this.myId + "\nExeStack : \n"+
                                this.exeStack.getReversed().toString() + "\n" +
                         "SymTable_" + this.myId + " : \n" +
                                this.symTableStack.toString() + "\n" +
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

    public MyIProcTable getProcTable() {
        return procTable;
    }
}