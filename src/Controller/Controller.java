package Controller;

import Model.Exceptions.MyExecutionException;
import Model.ProgramState.PrgState;
import Model.Value.RefIValue;
import Model.Value.IValue;
import Repository.RepositoryInterface;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements ControllerInterface {
    RepositoryInterface programStateQueue;
    ExecutorService executor;

    public RepositoryInterface getProgramStateQueue() {
        return programStateQueue;
    }

    public Controller(RepositoryInterface repository) {
        programStateQueue = repository;
    }

    public Controller() {
    }

    public void oneStepForAllPrg(List<PrgState> programList) throws Exception {
        List<Callable<PrgState>> callList =
                programList.stream()
                        .map((PrgState p) -> (Callable<PrgState>) (() -> {
                            return p.oneStep();
                        }))
                        .collect(Collectors.toList());


        List<PrgState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new MyExecutionException(e.toString());
                    }

                }).filter(p -> p != null)
                .collect(Collectors.toList());
        programList.addAll(newProgramList); ///ading to list the new programs
        programList.forEach(program -> { /// print
            try {
                programStateQueue.logPrgStateExec(program);
            } catch (Exception e) {
                throw new MyExecutionException(e.toString());
            }
        });
        programStateQueue.setPrgList(programList);
    }

    public void allStep() throws Exception {
        executor = Executors.newFixedThreadPool(3);
        List<PrgState> programList = removeCompletedPrg(programStateQueue.getPrgList());

        while (programList.size() > 0) {

            programList.forEach(program -> program.getHeap().setContent(
                    safeGarbageCollector(
                            getAddrFromSymTable(
                                    program.getSymTable().getContent().values(),
                                    program.getHeap().getContent().values()
                            ),
                            program.getHeap().getContent()
                    )
            ));


            try {
                oneStepForAllPrg(programList);
            } catch (Exception e) {
                e.printStackTrace();

            }
            programList = removeCompletedPrg(programStateQueue.getPrgList());
        }
        executor.shutdownNow();

        programStateQueue.setPrgList(programList);

    }
    public void oneStepView() throws Exception {
    executor =Executors.newFixedThreadPool(3);
    List<PrgState> programList = removeCompletedPrg(programStateQueue.getPrgList());

        programList.forEach(program -> program.getHeap().setContent(
                safeGarbageCollector(
                        getAddrFromSymTable(
                                program.getSymTable().getContent().values(),
                                program.getHeap().getContent().values()
                        ),
                        program.getHeap().getContent()
                )
        ));
        try {
            oneStepForAllPrg(programList);
        } catch (Exception e) {
            e.printStackTrace();
         }
        programList = removeCompletedPrg(programStateQueue.getPrgList());

        executor.shutdownNow();

        programStateQueue.setPrgList(programList);
}

    public Map<Integer, IValue> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap){
    return heap.entrySet().stream()
            .filter(e->symTableAddr.contains(e.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<IValue> symTableIValues, Collection<IValue> heap) {
        return Stream.concat(
                heap.stream()
                        .filter(v -> v instanceof RefIValue)
                        .map(v->  {
                            RefIValue v1 = (RefIValue) v; return v1.getAddr();}),

                symTableIValues.stream()
                        .filter(v -> v instanceof RefIValue)
                        .map(v -> { RefIValue v1 = (RefIValue) v; return v1.getAddr(); })

          ).collect(Collectors.toList());
    }

    public void addProgramState(PrgState program){
        this.programStateQueue.addProgram(program);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

}


