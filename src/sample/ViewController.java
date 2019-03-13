package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController {
    private Label map[][];
    private int map_size = 3;

    ViewController(Stage primaryStage) throws IOException {

        this.map = new Label[map_size][map_size];
        for(int i = 0;i < map_size;i++)
            for(int j = 0; j < map_size; j++)
            {
                map[i][j] = new Label("CELL" + i + j);
            }

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tick Tack");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }
}
