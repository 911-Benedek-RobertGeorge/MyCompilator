package Model.Dto;

import Model.Value.IValue;
import javafx.beans.property.SimpleStringProperty;

public class SymbolView {
    private SimpleStringProperty variableName;
    private SimpleStringProperty value;


    public SymbolView(String variableName, IValue IValue){
        this.variableName = new SimpleStringProperty(variableName);
        this.value = new SimpleStringProperty(IValue.toString());
    }

    public SimpleStringProperty variableNameProperty(){
        return this.variableName;
    }

    public SimpleStringProperty valueProperty(){
        return this.value;
    }

    public String getVariableName(){
        return this.variableName.get();
    }

    public String getValue(){
        return this.value.get();
    }

    public void setVariableName(String newVariableName){
        this.variableName.set(newVariableName);
    }

    public void setValue(String newValue){
        this.value.set(newValue);
    }
}
