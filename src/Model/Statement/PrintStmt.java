package Model.Statement;

import Model.Containers.OutList.MyIList;
import Model.Containers.SymTable.MyIDictionary;
import Model.Exp.Exp;
import Model.ProgramState.PrgState;
import Model.Type.Type;
import Model.Value.IValue;

public class PrintStmt implements IStmt{
     private Exp expresion;

     public PrintStmt(Exp exp){
         expresion = exp;
     }



    @Override
    public PrgState execute(PrgState state) throws Exception {

        MyIList list = state.getOut();
        IValue val = expresion.eval(state.getSymTableStack().peek(),state.getHeap());
        list.add(val);
        state.setOut(list);
        return null;
    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        expresion.typecheck(typeEnv);
        return typeEnv;
    }
    @Override
    public String toString(){
        return "print(" +expresion.toString()+")" ;
    }
}
