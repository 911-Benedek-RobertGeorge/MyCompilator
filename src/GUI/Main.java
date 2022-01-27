package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ///main window
        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 1000, 600);
        MainWindowController mainController = fxmlLoader1.getController();

        ///list with programs
        FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("firstWindow.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 1050, 500);
        FirstWindowController firstWindowController = fxmlLoader2.getController();

        mainController.setSecondaryController(firstWindowController);

        Stage mainStage = new Stage();
        mainStage.setTitle("Main widow");
        mainStage.setScene(scene1);
        mainStage.show();

        primaryStage.setTitle("Select a program");
        primaryStage.setScene(scene2);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
