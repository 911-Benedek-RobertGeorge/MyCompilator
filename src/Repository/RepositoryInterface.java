package Repository;

import Model.Exceptions.ContainersException;
import Model.ProgramState.PrgState;

import java.util.List;

public interface RepositoryInterface {
     public void addProgram(PrgState program);
    public PrgState getSpecificProgram(int index) throws ContainersException;
    void logPrgStateExec(PrgState progrma) throws Exception;
    List<PrgState> getPrgList();
    public void setPrgList(List<PrgState> newState);
    }
