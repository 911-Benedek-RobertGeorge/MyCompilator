package Repository;

import Model.Exceptions.ContainersException;
import Model.Exceptions.FilesException;
import Model.ProgramState.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface{
    private List<PrgState> programStateQueue;
    private String logFilePath;

    public Repository(PrgState program,String filePath){
        programStateQueue = new ArrayList<>();
        programStateQueue.add(program);
        logFilePath = filePath;
        try {
            String fileName = this.logFilePath;
            File file = new File(fileName);
            if (file.exists()) {
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                raf.setLength(0);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    @Override
    public void addProgram(PrgState program) {
        programStateQueue.add(program);
    }

    @Override
    public PrgState getSpecificProgram(int index) throws ContainersException {

        if(programStateQueue.size() <= index || index < 0)
            throw new ContainersException("The index is out of program state queue");
        return programStateQueue.get(index);
    }

    @Override
    public void logPrgStateExec(PrgState program) throws Exception {

       try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))){
            logFile.println(program.toString());
       }
       catch (Exception e){
           throw new FilesException(e.toString());
       }
    }
    public List<PrgState> getPrgList(){
        return this.programStateQueue;
    }
    public void setPrgList(List<PrgState> newState){
        this.programStateQueue = newState;
    }

}
