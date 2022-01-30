package GUI;

import Model.Containers.ExeStack.MyIStack;
import Model.Containers.Heap.MyIHeap;
import Model.Containers.OutList.MyIList;
import Model.Containers.SymTable.MyIDictionary;
import Model.Dto.HeapView;
import Model.Dto.LatchView;
import Model.Dto.SymbolView;
import Model.Exceptions.ContainersException;
import Model.Exp.ValueExp;
import Model.ProgramState.PrgState;
import Model.Statement.IStmt;
import Model.Value.IValue;
import Model.Value.IntIValue;
import View.RunCommand;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.util.List;

public class MainWindowController {

    private FirstWindowController selectController;
    private PrgState curentProgram;

    @FXML
    private TableView<HeapView> heapTableView;
    @FXML
    private TableColumn<HeapView, java.lang.String> addressColumn;
    @FXML
    private TableColumn<HeapView, java.lang.String> valueHeapColumn;

    @FXML
    private TableView<LatchView> latchTableView;
    @FXML
    private TableColumn<LatchView, java.lang.String> latchAdressColumn;
    @FXML
    private TableColumn<LatchView, java.lang.String> latchValueColumn;

    @FXML
    private TableView<SymbolView> symbolTableView;
    @FXML
    private TableColumn<SymbolView, java.lang.String> variableNameColumn;
    @FXML
    private TableColumn<SymbolView, java.lang.String> valueSymColumn;

    @FXML
    private ListView<IValue> outTableView;

    @FXML
    private ListView<java.lang.String> fileTableView;
    @FXML
    private ListView<PrgState> programStateView;

    @FXML
    private ListView<IStmt> executionStackView;

    @FXML
    private TextField programStatesText;

    @FXML
    private Button runOneStepButton;

    public void setSecondaryController(FirstWindowController  newSelectController){

        this.selectController = newSelectController;

        this.selectController.getCommandListView().getSelectionModel().selectedItemProperty().addListener((a,b,ex)-> {
            try {
                this.refreshMainWindow(ex);
            } catch (ContainersException e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    private void initialize( ) {
        this.programStateView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.addressColumn.setCellValueFactory(new PropertyValueFactory<HeapView, java.lang.String>("heapAddress"));
        this.valueHeapColumn.setCellValueFactory(new PropertyValueFactory<HeapView, java.lang.String>("heapValue"));

        this.variableNameColumn.setCellValueFactory(new PropertyValueFactory<SymbolView, java.lang.String>("variableName"));
        this.valueSymColumn.setCellValueFactory(new PropertyValueFactory<SymbolView, java.lang.String>("value"));

        this.latchAdressColumn.setCellValueFactory(new PropertyValueFactory<LatchView, java.lang.String>("address"));
        this.latchValueColumn.setCellValueFactory(new PropertyValueFactory<LatchView, java.lang.String>("value"));

        this.outTableView.setCellFactory(TextFieldListCell.forListView(new StringConverter<IValue>() {
            @Override
            public String toString(IValue IValue) {
                return IValue.toString();
            }

            @Override
            public IValue fromString(java.lang.String str) {
                return null;
            }
        }));
        this.fileTableView.setCellFactory(TextFieldListCell.forListView(new StringConverter<String>() {
            @Override
            public java.lang.String toString(java.lang.String s) {
                return s;
            }
            @Override
            public java.lang.String fromString(java.lang.String str) {
                return null;
            }
        }));
        this.executionStackView.setCellFactory(TextFieldListCell.forListView(new StringConverter<IStmt>() {
            @Override
            public java.lang.String toString(IStmt statement) {
                return statement.toString();
            }

            @Override
            public IStmt fromString(java.lang.String str) {
                return null;
            }
        }));
        this.programStateView.setCellFactory(TextFieldListCell.forListView(new StringConverter<PrgState>() {
            @Override
            public java.lang.String toString(PrgState program) {
                return Integer.toString(program.getMyId());
            }

            @Override
            public PrgState fromString(java.lang.String str) {
                return null;
            }
        }));

        this.programStateView.getSelectionModel().selectedItemProperty().addListener((a,b,state)-> {
            if(state != null)
                updateDataForCurentProgram(state);
        });
        this.runOneStepButton.setOnAction(actionEvent -> runOneStepHandler(this.selectController.getCommandListView().getSelectionModel().getSelectedItems().get(0)));
    }

    private void runOneStepHandler(RunCommand ex){
        try{
            System.out.println(ex.getController().getProgramStateQueue().getPrgList().get(0));

            ex.getController().oneStepView();
            refreshMainWindow(ex);
        }
        catch (Exception e){
            e.getStackTrace();
        }

    }

    private void refreshMainWindow(RunCommand command) throws ContainersException {
        this.symbolTableView.getItems().clear();
        this.heapTableView.getItems().clear();
        this.executionStackView.getItems().clear();
        this.programStateView.getItems().clear();
        this.outTableView.getItems().clear();
        this.fileTableView.getItems().clear();

        this.latchTableView.getItems().clear();

        List<PrgState> programStates = command.getController().getProgramStateQueue().getPrgList();

        if(programStates.size() != 0)
            this.curentProgram = programStates.get(0);
         ///get new values
        MyIHeap<Integer, IValue> sharedHeap = this.curentProgram.getHeap();
        MyIDictionary<java.lang.String, BufferedReader> fileTable = this.curentProgram.getFileTable();
        MyIList<IValue> output = this.curentProgram.getOut();

        ///update
        sharedHeap.getContent().forEach((address, value)->this.heapTableView.getItems().add(new HeapView(address, value)));
        fileTable.getContent().forEach((fileName, filePath)
                -> this.fileTableView.getItems().add(fileName.toString()));
        output.getContent().forEach((value)->this.outTableView.getItems().add(value));
        programStates.forEach((programState)->this.programStateView.getItems().add(programState));

        this.curentProgram.getLatchTable().getContent().forEach((address,value) -> this.latchTableView.getItems().add(new LatchView(address, value)));

        this.programStatesText.setText(Integer.toString(programStates.size()));

    }

    public void updateDataForCurentProgram(PrgState theProgram)
    {
        this.symbolTableView.getItems().clear();
        this.executionStackView.getItems().clear();

        ///get new values
        MyIStack<IStmt> executionStack = theProgram.getExeStack();
        MyIDictionary<String, IValue> symbolTable = theProgram.getSymTable();

        ///update
        executionStack.getReversed().forEach((statement)->this.executionStackView.getItems().add(statement));
        symbolTable.getContent().forEach((name, value)->this.symbolTableView.getItems().add(new SymbolView(name, value)));
    }

}
