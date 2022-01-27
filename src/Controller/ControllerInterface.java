package Controller;

import Model.ProgramState.PrgState;

import java.util.List;

public interface ControllerInterface {
    public void allStep() throws Exception;
    public void addProgramState(PrgState program);
    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList);
    }
