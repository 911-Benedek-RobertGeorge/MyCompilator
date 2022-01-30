package Model.Containers.ProcTable;

import Model.Exceptions.ContainersException;
import Model.Statement.IStmt;

import java.util.List;
import java.util.Map;

public interface MyIProcTable {
    public void add(String name,  Pair<List<String>, IStmt> value) throws ContainersException;
    public Map<String, Pair<List<String>, IStmt> > getContent();
    public void setContent(Map<String, Pair<List<String>, IStmt>>  refreshedHeap);
    }
