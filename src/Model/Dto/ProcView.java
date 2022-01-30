package Model.Dto;

import Model.Value.IValue;
import javafx.beans.property.SimpleStringProperty;

public class ProcView {
    private SimpleStringProperty definition;
    private SimpleStringProperty body;

    public ProcView(String definition, String body) {
        this.definition = new SimpleStringProperty(definition);
        this.body = new SimpleStringProperty(body);
    }

    public SimpleStringProperty definitionProperty(){
        return this.definition;
    }

    public SimpleStringProperty bodyProperty(){
        return this.body;
    }


    public String getDefinition() {
        return definition.get();
    }

    public void setDefinition(String definition) {
        this.definition.set(definition);
    }

    public String getBody() {
        return body.get();
    }

    public void setBody(String body) {
        this.body.set(body);
    }
}
