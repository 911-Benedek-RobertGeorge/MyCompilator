package View;

import java.util.HashMap;
import java.util.Map;

public class TextMenu {
    private Map<String, Command> commands;

    public Map<String, Command> getCommands() {
        return commands;
    }

    public TextMenu() {
        commands = new HashMap<>();
    }

    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    private void printMenu() {
        for (Command com : commands.values()) {
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }
    public void show(){
        this.printMenu();
    }
    public void runExemple(String exemple){
         if(commands.get(exemple) == null)
         {
             System.out.println("You already run that OUT or the OUT does not exist.");
             return;
         }
         System.out.println(commands.get(exemple).getDescription());
         commands.get(exemple).execute();
         commands.remove(exemple);
    }
}