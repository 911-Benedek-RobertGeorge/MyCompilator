package View;
import Controller.Controller;
import Model.Containers.ExeStack.MyIStack;
import Model.Containers.ExeStack.MyStack;
import Model.Containers.OutList.MyIList;
import Model.Containers.OutList.MyList;
import Model.Containers.SymTable.MyDictionary;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exp.*;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolIValue;
import Model.Value.IntIValue;
import Model.Value.String;
import Model.Value.IValue;
import Repository.Repository;
import Repository.RepositoryInterface;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter  {




    private static TextMenu menu ;

    public static void main(java.lang.String[] args)  throws Exception {
    Scanner myInput = new Scanner( System.in );
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    /*System.out.println("Enter Prog log file path : ");
    String filePath =   myObj.nextLine();*/
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
    symTable = new MyDictionary<java.lang.String, IValue>();
    output = new MyList<IValue>();
    IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntIValue(20))),
                    new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                            new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntIValue(30))),
                                    new PrintStmt(new ArithExp('+',new ReadHeapExp(new VarExp("v")), new ValueExp(new IntIValue(5))))))));
    PrgState prg7 = new PrgState(stack,symTable,output,ex7);
    RepositoryInterface repo7= new Repository(prg7, fileLab7);
    Controller ctr7 = new Controller(repo7);


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


    menu = new TextMenu();
    menu.addCommand(new ExitCommand("0", "exit"));
    menu.addCommand(new RunCommand("1", ex1.toString(), ctr1));
    menu.addCommand(new RunCommand("2", ex2.toString(), ctr2));
    menu.addCommand(new RunCommand("3", ex3.toString(), ctr3));
    menu.addCommand(new RunCommand("4", ex4.toString(),ctr4));
    menu.addCommand(new RunCommand("5", ex5.toString(),ctr5));
    menu.addCommand(new RunCommand("6", ex6.toString(),ctr6));
    menu.addCommand(new RunCommand("7", ex7.toString(),ctr7));
    menu.addCommand(new RunCommand("9", ex9.toString(),controller9));
    menu.addCommand(new RunCommand("10", ex10.toString(),controller10));
    menu.addCommand(new RunCommand("11", ex11.toString(),controller11));


    menu.show();
    java.lang.String comand ="";



    while (comand != "0"){
        comand = myObj.nextLine();
        menu.runExemple(comand);
    }

    }

    private List<java.lang.String> getListDescriptions(){
        List<java.lang.String> commandList = new ArrayList<>();
        for (Command command : menu.getCommands().values()){
            commandList.add(command.getKey() + " -> " +  command.getDescription());
        }
         return commandList;
    }

}

