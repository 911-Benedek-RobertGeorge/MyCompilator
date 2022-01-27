package View;

import Controller.Controller;

public class RunCommand extends Command {


    private Controller controller;
    public Controller getController() {
        return controller;
    }
    public RunCommand(String key, String description, Controller ctr) {
        super(key, description);
        this.controller = ctr;
    }

    @Override
    public void execute() {
        try {
            controller.allStep();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        //here you must treat the exceptions that can not be solved in the controller    }
    }
}
