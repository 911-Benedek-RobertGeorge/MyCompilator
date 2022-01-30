package Model.Dto;

import Model.Value.IValue;
import javafx.beans.property.SimpleStringProperty;

public class LatchView {
    private SimpleStringProperty address;
    private SimpleStringProperty value;

    public LatchView(Integer heapAddress, Integer heapIValue){
        this.address = new SimpleStringProperty(Integer.toString(heapAddress));
        this.value = new SimpleStringProperty(heapIValue.toString());
    }

    public SimpleStringProperty addressProperty(){
        return this.address;
    }

    public SimpleStringProperty valueProperty(){
        return this.value;
    }

    public String getAddress(){
        return this.address.get();
    }

    public String getValue(){
        return this.value.get();
    }

    public void setAddress(Integer newHeapAddress){
        this.address.set(String.valueOf(newHeapAddress));
    }

    public void setValue(IValue newHeapIValue){
        this.value.set(newHeapIValue.toString());
    }
}
