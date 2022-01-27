package Model.Dto;

import Model.Value.IValue;
import javafx.beans.property.SimpleStringProperty;

public class HeapView{

    private SimpleStringProperty heapAddress;
    private SimpleStringProperty heapValue;

    public HeapView(Integer heapAddress, IValue heapIValue){
        this.heapAddress = new SimpleStringProperty(Integer.toString(heapAddress));
        this.heapValue = new SimpleStringProperty(heapIValue.toString());
    }

    public SimpleStringProperty heapAddressProperty(){
        return this.heapAddress;
    }

    public SimpleStringProperty heapValueProperty(){
        return this.heapValue;
    }

    public String getHeapAddress(){
        return this.heapAddress.get();
    }

    public String getHeapValue(){
        return this.heapValue.get();
    }

    public void setHeapAddress(Integer newHeapAddress){
        this.heapAddress.set(String.valueOf(newHeapAddress));
    }

    public void setHeapValue(IValue newHeapIValue){
        this.heapValue.set(newHeapIValue.toString());
    }

}
