package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewController viewController = new ViewController(primaryStage);
        Controller controller = new Controller(viewController);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
