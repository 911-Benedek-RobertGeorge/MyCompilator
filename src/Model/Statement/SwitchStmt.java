package Model.Statement;

import Model.Containers.Heap.MyIHeap;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.TypeCheckException;
import Model.Exp.Exp;
import Model.Exp.ReadHeapExp;
import Model.Exp.RelationalExp;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.BoolIValue;
import Model.Value.IValue;

import javax.management.openmbean.TabularType;

public class SwitchStmt implements IStmt{

    private Exp condition;
    private Exp case1;
    private IStmt case1Stmt;
    private  Exp case2;
    private IStmt case2Stmt;
    private IStmt defaultStmt;

    public SwitchStmt(Exp condition, Exp case1, IStmt case1Stmt, Exp case2, IStmt case2Stmt, IStmt defaultStmt) {
        this.condition = condition;
        this.case1 = case1;
        this.case1Stmt = case1Stmt;
        this.case2 = case2;
        this.case2Stmt = case2Stmt;
        this.defaultStmt = defaultStmt;
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIDictionary<String,IValue> symTable = state.getSymTable();
        MyIHeap<Integer,IValue> heapTable = state.getHeap();
        IValue val1 = condition.eval(symTable,heapTable);
        IValue val2 = case1.eval(symTable,heapTable);
        IValue val3 = case2.eval(symTable,heapTable);
        IStmt elseStmt = new IfStmt(new RelationalExp("==",condition,case2),case2Stmt,defaultStmt);
        IStmt ifStmt = new IfStmt(new RelationalExp("==",condition,case1),case1Stmt,elseStmt);
        state.getExeStack().push(ifStmt);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        Type expType = this.condition.typecheck(typeEnv);
        Type case1Type = this.case1.typecheck(typeEnv);
        Type case2Type = this.case1.typecheck(typeEnv);
        if(expType.equals(case1Type) && expType.equals(case2Type))
        {
            return defaultStmt.typecheck(case2Stmt.typecheck(case1Stmt.typecheck(typeEnv)));
        }else{
            throw new TypeCheckException("Switch Statement : The types of expresion are not the same");
        }

    }

    @Override
    public String toString() {
        return "(Switch(" + condition + ")" +
                "\t(case (" + case1 + ")" +
                " : " +    case1Stmt + ")"+
                "\t(case (" + case2 +
                ") : " + case2Stmt + ")" +
                "\t(default : " + defaultStmt +"))";
    }
}
