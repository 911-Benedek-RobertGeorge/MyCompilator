package GUI;

import Controller.Controller;
import Model.Containers.ExeStack.MyIStack;
import Model.Containers.ExeStack.MyStack;
import Model.Containers.OutList.MyIList;
import Model.Containers.OutList.MyList;
import Model.Containers.ProcTable.MyIProcTable;
import Model.Containers.ProcTable.MyProcTable;
import Model.Containers.ProcTable.Pair;
import Model.Containers.SymTable.MyDictionary;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exp.*;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.BoolIValue;
import Model.Value.IntIValue;
import Model.Value.String;
import Model.Value.IValue;
import Repository.Repository;
import Repository.RepositoryInterface;
import View.RunCommand;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class FirstWindowController {

    @FXML
    private ListView<RunCommand> commandListView;

    public ListView<RunCommand> getCommandListView(){
        return this.commandListView;
    }

    public Controller createController(IStmt exercise, java.lang.String fileName) throws Exception {
        MyIStack<IStmt> executionStack = new MyStack<>();
        MyIDictionary<java.lang.String, IValue> symbolTable  = new MyDictionary<>();
        MyIList<IValue> outList  = new MyList<>();

        //exercise.typecheck(symbolTable);

        PrgState state  = new PrgState(executionStack , symbolTable , outList , exercise);
        Repository repository  = new Repository(state , fileName);

        Controller controller  = new Controller(repository );

        return controller;
    }

    @FXML
    public void initialize() throws Exception{


            java.lang.String filePath = "D:\\faculta\\MAP\\Lab3-A2\\src\\Files\\OUT";
            java.lang.String fileLab7 = "D:\\faculta\\MAP\\Lab3-A2\\src\\Files\\Lab7";



            MyIStack stack = new MyStack<IStmt>();
            MyIDictionary symTable = new MyDictionary<java.lang.String, IValue>();
            MyIList output = new MyList<IValue>();

            IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntIValue(2))),
                            new PrintStmt(new VarExp("v"))));
            PrgState prg1 = new PrgState(stack,symTable,output,ex1);
            RepositoryInterface repo1 = new Repository(prg1, "log1.txt");
            Controller ctr1 = new Controller(repo1);


            stack = new MyStack<IStmt>();
            symTable = new MyDictionary<java.lang.String, IValue>();
            output = new MyList<IValue>();
            IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntIValue(2)), new ArithExp('/', new ValueExp(new IntIValue(3)), new ValueExp(new IntIValue(0))))),
                                    new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"),
                                            new ValueExp(new IntIValue(1)))), new PrintStmt(new VarExp("b"))))));

            PrgState prg2 = new PrgState(stack,symTable,output,ex2);
            RepositoryInterface repo2 = new Repository(prg2, filePath);
            Controller ctr2 = new Controller(repo2);

            stack = new MyStack<IStmt>();
            symTable = new MyDictionary<java.lang.String, IValue>();
            output = new MyList<IValue>();
            IStmt ex3 =  new CompStmt(new VarDeclStmt("a", new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new BoolIValue(true))),
                                    new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntIValue(2))),
                                            new AssignStmt("v", new ValueExp(new IntIValue(3)))),
                                            new PrintStmt(new VarExp("v"))))));
            PrgState prg3 = new PrgState(stack,symTable,output,ex3);
            RepositoryInterface repo3 = new Repository(prg3, filePath);
            Controller ctr3 = new Controller(repo3);


            stack = new MyStack<IStmt>();
            symTable = new MyDictionary<java.lang.String, IValue>();
            output = new MyList<IValue>();
            IStmt ex4= new CompStmt(new VarDeclStmt("path",new StringType()),
                    new CompStmt(new AssignStmt("path",new ValueExp(new String("D:\\faculta\\MAP\\Lab3-A2\\src\\Files\\numbers"))),
                            new CompStmt(new VarDeclStmt("number",new IntType()),
                                    new CompStmt(new OpenRFile(new VarExp("path")),
                                            new CompStmt(new ReadFile(new VarExp("path"),"number"),

                                                    new CompStmt(new PrintStmt(new VarExp("number")),
                                                            new CompStmt(new ReadFile(new VarExp("path"),"number"),
                                                                    new CompStmt(new PrintStmt(new VarExp("number")) ,
                                                                            new CloseRFile(new VarExp("path"))))))))));
            PrgState prg4 = new PrgState(stack,symTable,output,ex4);
            RepositoryInterface repo4= new Repository(prg4, filePath);
            Controller ctr4 = new Controller(repo4);


            stack = new MyStack<IStmt>();
            symTable = new MyDictionary<java.lang.String, IValue>();
            output = new MyList<IValue>();
            IStmt ex5 = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new IntIValue(5))),
                                    new CompStmt(new AssignStmt("b", new ValueExp(new IntIValue(8))),
                                            new IfStmt(new RelationalExp("<=",new VarExp("a"),new VarExp("b")),
                                                    new PrintStmt(new VarExp("a")),new PrintStmt(new VarExp("b"))) ))));


            PrgState prg5 = new PrgState(stack,symTable,output,ex5);
            RepositoryInterface repo5= new Repository(prg5, filePath);
            Controller ctr5 = new Controller(repo5);

            stack = new MyStack<IStmt>();
            symTable = new MyDictionary<java.lang.String, IValue>();
            output = new MyList<IValue>();
            IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntIValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new HeapAllocationStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                    new PrintStmt(new ArithExp('+',new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), new ValueExp(new IntIValue(5)))))))));
            PrgState prg6 = new PrgState(stack,symTable,output,ex6);
            RepositoryInterface repo6= new Repository(prg6, fileLab7);
            Controller ctr6 = new Controller(repo6);

            stack = new MyStack<IStmt>();
            MyIDictionary symTable7 = new MyDictionary<java.lang.String, IValue>();
            output = new MyList<IValue>();
            IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntIValue(20))),
                            new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                    new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntIValue(30))),
                                            new PrintStmt(new ArithExp('+',new ReadHeapExp(new VarExp("v")), new ValueExp(new IntIValue(5))))))));
            PrgState prg7 = new PrgState(stack,symTable7,output,ex7);
            RepositoryInterface repo7= new Repository(prg7, fileLab7);
            Controller ctr7 = new Controller(repo7);

            MyIStack stack8 = new MyStack<IStmt>();
            MyIDictionary symTable8 = new MyDictionary<java.lang.String, IValue>();
            MyIList output8 = new MyList<IValue>();

            IStmt ex8 = new CompStmt(new VarDeclStmt("WorksFine", new IntType()),
                    new CompStmt(new AssignStmt("WorksFine", new ValueExp(new IntIValue(2))),
                            new PrintStmt(new VarExp("WorksFine"))));
            PrgState prg8 = new PrgState(stack8,symTable8,output8,ex8);
            RepositoryInterface repo8 = new Repository(prg8, "log1.txt");
            Controller ctr8 = new Controller(repo8);



        MyIStack<IStmt> executionStack9 = new MyStack<>();
            MyIDictionary<java.lang.String, IValue> symbolTable9 = new MyDictionary<>();
            MyIList<IValue> outList9 = new MyList<>();
            IStmt declare_v = new VarDeclStmt("v", new IntType());
            IStmt assignToV = new AssignStmt("v", new ValueExp(new IntIValue(4)));
            IStmt whileStatement = new WhileStmt(new RelationalExp(">",new VarExp("v"),
                    new ValueExp(new IntIValue(0)) ), new CompStmt(new PrintStmt( new
                    VarExp("v")), new AssignStmt("v", new ArithExp('-',
                    new VarExp("v"), new ValueExp( new IntIValue(1))))));
            IStmt printStatement = new PrintStmt(new VarExp("v"));
            IStmt ex9 = new CompStmt(declare_v, new CompStmt(assignToV, new CompStmt(
                    whileStatement, printStatement)));
            PrgState state9 = new PrgState(executionStack9, symbolTable9, outList9, ex9);
            Repository repository9 = new Repository(state9, "log9.txt");
            Controller controller9 = new Controller(repository9);


            MyIStack<IStmt> executionStack10 = new MyStack<>();
            MyIDictionary<java.lang.String, IValue> symbolTable10 = new MyDictionary<>();
            MyIList<IValue> outList10 = new MyList<>();
            IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntIValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new HeapAllocationStmt("a", new VarExp("v")),
                                            new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntIValue(30))),
                                                    new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
            PrgState state10 = new PrgState(executionStack10, symbolTable10, outList10, ex10);
            Repository repository10 = new Repository(state10, "log10.txt");
            repository10.addProgram(prg1);
            repository10.addProgram(prg3);
            Controller controller10 = new Controller(repository10);


            MyIStack<IStmt> executionStack11 = new MyStack<>();
            MyIDictionary<java.lang.String, IValue> symbolTable11 = new MyDictionary<>();
            MyIList<IValue> outList11 = new MyList<>();
            IStmt declare_v_11 = new VarDeclStmt("v", new IntType());
            IStmt assignToV_11 = new AssignStmt("v", new ValueExp(new IntIValue(10)));
            IStmt refIntA = new VarDeclStmt("a", new RefType(new IntType()));
            IStmt newValueA = new HeapAllocationStmt("a", new ValueExp(new IntIValue(22)));
            IStmt printStatement_11 = new PrintStmt(new VarExp("v"));
            IStmt printRefValue = new PrintStmt( new ReadHeapExp(new VarExp("a")));
            IStmt forkStmt = new ForkStmt(
                    new CompStmt(new WriteHeapStmt("a",new ValueExp(new IntIValue(30))),
                            new CompStmt(new AssignStmt("v", new ValueExp(new IntIValue(32))),
                                    new CompStmt(
                                            new ForkStmt(new WriteHeapStmt("a",new ValueExp(new IntIValue(50)))),
                                            new CompStmt(printStatement_11,printRefValue)))));

            IStmt ex11 = new CompStmt(declare_v_11,
                    new CompStmt(assignToV_11,
                            new CompStmt(refIntA,
                                    new CompStmt(newValueA,
                                            new CompStmt(forkStmt,
                                                    new CompStmt(printStatement_11,printRefValue)))
                            )));
            ex11.typecheck(symTable);

            PrgState state11 = new PrgState(executionStack11, symbolTable11, outList11, ex11);
            Repository repository11 = new Repository(state11, "log11.txt");
            Controller controller11 = new Controller(repository11);




        IStmt declareV1 = new VarDeclStmt("v1", new IntType());
        IStmt assignV1 = new AssignStmt("v1", new ValueExp(new IntIValue(2)));
        IStmt declareV2 = new VarDeclStmt("v2", new IntType());
        IStmt assignV2 = new AssignStmt("v2", new ValueExp(new IntIValue(3)));
        IStmt printStmt = new PrintStmt(new VarExp("v1"));
        IStmt ifStmt = new IfStmt(new VarExp("v1"),
                new PrintStmt( new MulExp(new VarExp("v1"),new VarExp("v2"))),
                printStmt);   //if(exp,then,else)

        IStmt ex12 = new CompStmt(declareV1,new CompStmt(assignV1,
                     new CompStmt(declareV2,new CompStmt(assignV2,ifStmt))));

        Controller controller12 = createController(ex12,"log12.txt");

        IStmt declareV13 = new VarDeclStmt("v", new IntType());
        IStmt assignV13 = new AssignStmt("v", new ValueExp(new IntIValue(20)));
        IStmt waitStmt = new WaitStmt(new IntIValue(4));
        IStmt printArith = new PrintStmt(new ArithExp('*',new VarExp("v"),new ValueExp(new IntIValue(10))));
        //arithExp(char,exp1,exp2)

        IStmt ex13 = new CompStmt(declareV13,new CompStmt( assignV13,
                new CompStmt(waitStmt,printArith)));
        ex13.typecheck(symTable) ;
       Controller controller13 = createController(ex13,"log13.txt");




       /// PROCEDURE TABLE

        MyIStack<IStmt> executionStack14 = new MyStack<>();
        MyIDictionary<java.lang.String, IValue> symbolTable14 = new MyDictionary<>();
        MyIList<IValue> outList14 = new MyList<>();
        MyIProcTable procTable = new MyProcTable();
        List<java.lang.String> l1 = new ArrayList<>();
        l1.add("a");
        l1.add("b");
        IStmt body1 =  new CompStmt(new AssignStmt("v",new ArithExp('+',new VarExp("a"),new VarExp("b"))),new PrintStmt(new VarExp("v")));
        IStmt body2=  new CompStmt(new AssignStmt("v",new ArithExp('*',new VarExp("a"),new VarExp("b"))),new PrintStmt(new VarExp("v")));

        procTable.add("sum", new Pair(l1,body1));
        procTable.add("product", new Pair(l1,body2));

        IStmt declareV14 = new VarDeclStmt("v", new IntType());
        IStmt assignV14 = new AssignStmt("v", new ValueExp(new IntIValue(2)));
        IStmt declareW= new VarDeclStmt("w", new IntType());
        IStmt assignW = new AssignStmt("w", new ValueExp(new IntIValue(5)));
        List<Exp> variablesSum = new ArrayList<>();
        variablesSum.add(new ArithExp('*',new VarExp ("v"),new ValueExp(new IntIValue(10))));
        variablesSum.add(new   VarExp ("w") );
        IStmt callProcSum = new CallStmt("sum",variablesSum);
        List<Exp> variablesProd = new ArrayList<>();
        variablesProd.add(new   VarExp ("v") );
        variablesProd.add(new   VarExp ("w") );
        IStmt callProcProd = new CallStmt("product",variablesProd);
        IStmt printV = new PrintStmt(new VarExp("v"));
        IStmt forkStmt14 = new ForkStmt(new CompStmt(callProcProd,
                new ForkStmt(new CallStmt("sum",variablesProd))));

        IStmt ex14 = new CompStmt(declareV14, new CompStmt(assignV14,
                new CompStmt(declareW,new  CompStmt(assignW,
                       new CompStmt(callProcSum,
                               new CompStmt(printV,
                                       forkStmt14)) ))));

        PrgState state14 = new PrgState(executionStack14, symbolTable14, outList14,procTable, ex14);
        Repository repository14 = new Repository(state14, "log14.txt");
        Controller controller14 = new Controller(repository14);

        this.commandListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<RunCommand>() {
            @Override
            public java.lang.String toString(RunCommand runExampleCommand) {
                return runExampleCommand.toString();
            }

            @Override
            public RunCommand fromString(java.lang.String str) {
                return null;
            }
        }));


        this.commandListView.getItems().add(new RunCommand("1", ex1.toString(), ctr1));
        this.commandListView.getItems().add(new RunCommand("2", ex2.toString(), ctr2));
        this.commandListView.getItems().add(new RunCommand("3", ex3.toString(), ctr3));
        this.commandListView.getItems().add(new RunCommand("4", ex4.toString(),ctr4));
        this.commandListView.getItems().add(new RunCommand("5", ex5.toString(),ctr5));
        this.commandListView.getItems().add(new RunCommand("6", ex6.toString(),ctr6));
        this.commandListView.getItems().add(new RunCommand("7", ex7.toString(),ctr7));
        this.commandListView.getItems().add(new RunCommand("8", ex8.toString(),ctr8));
        this.commandListView.getItems().add(new RunCommand("9", ex9.toString(),controller9));
        this.commandListView.getItems().add(new RunCommand("10", ex10.toString(),controller10));
        this.commandListView.getItems().add(new RunCommand("11", ex11.toString(),controller11));
        this.commandListView.getItems().add(new RunCommand("12", ex12.toString(),controller12));
        this.commandListView.getItems().add(new RunCommand("13", ex13.toString(),controller13));
        this.commandListView.getItems().add(new RunCommand("14", ex14.toString(),controller14));

        this.commandListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
}
