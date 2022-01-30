package Model.Statement;

import Model.Containers.SymTable.MyIDictionary;
import Model.Exceptions.TypeCheckException;
import Model.Exp.ValueExp;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IValue;
import Model.Value.IntIValue;

import java.awt.color.CMMException;

public class WaitStmt implements IStmt {
    private IValue number;


    public WaitStmt(IValue number) {
        this.number = number;
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        if(number.getType().equals(new IntType()))
        {
            if(((IntIValue )number).getVal()==0){
                System.out.println("its 0000");
                return null;
            } else
            {
                state.getExeStack().push(new CompStmt(new PrintStmt(new ValueExp( number)),
                        new WaitStmt(new IntIValue( ((IntIValue)number).getVal() -1  ))));
             }
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws Exception {
        if(number.getType().equals(new IntType()))
            return typeEnv;
        else {
            throw new TypeCheckException("Wait Statement : the type of the variable must be an int");
        }
    }
    public String toString() {
        return "Wait(" + number + ")";
    }
}
