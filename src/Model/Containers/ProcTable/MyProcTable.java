package Model.Containers.ProcTable;

import Model.Containers.Heap.MyHeap;
import Model.Containers.Heap.MyIHeap;
import Model.Exceptions.ContainersException;
import Model.Statement.IStmt;
import com.sun.jdi.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyProcTable implements MyIProcTable{

    private Map<String, Pair<List<String>, IStmt>> procTable;

    public MyProcTable(){
        procTable = new HashMap<String, Pair<List<String>, IStmt>>() ;
     }

    public void setContent(Map<String, Pair<List<String>, IStmt>>  refreshedHeap){
        procTable = refreshedHeap;
    }
    public Map<String, Pair<List<String>, IStmt> > getContent(){
        return procTable;
    }

    public void add(String name,  Pair<List<String>, IStmt> value) throws ContainersException {
        procTable.put(name, value);
    }


    @Override
    public String toString(){
        return procTable.toString();
    }
}
