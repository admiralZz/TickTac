package sample;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController {
    private VBox root;
    private HBox settingsPanel;
    private Pane gamePanel;

    private Label map[][];
    private int map_size = 3;

    ViewController(Stage primaryStage) throws IOException {
        // Элемент - родитель
        root = new VBox();

        /** Панель с настройками игры */
        settingsPanel = new HBox();
        // Устанавливаем отступы для всех компонентов контейнера
        int margin = 10;
        VBox.setMargin(settingsPanel,new Insets(margin,margin,margin,margin));
        // Контейнер с режимами игры
        VBox modePanel = new VBox();
        Label labelGameMode = new Label("Game Mode");
        // Переключатели режима
        VBox rbPanel = new VBox();
        VBox.setMargin(rbPanel, new Insets(5,0,0,0));
        RadioButton rbPvp = new RadioButton("PvP");
        RadioButton rbAiEasy = new RadioButton("AI(Easy)");
        RadioButton rbAiHard = new RadioButton("AI(Hard)");
        ToggleGroup gameModes = new ToggleGroup();
        // Объединяем в группе(для выбора только одного из вариантов)
        rbPvp.setToggleGroup(gameModes);
        rbAiEasy.setToggleGroup(gameModes);
        rbAiHard.setToggleGroup(gameModes);
        rbPvp.setSelected(true);
        rbPanel.getChildren().addAll(rbPvp, rbAiEasy, rbAiHard);
        // Группируем название и кнопки вместе
        modePanel.getChildren().addAll(labelGameMode, rbPanel);
        settingsPanel.getChildren().add(modePanel);

        /** Разделители */
        Separator separator1 = new Separator();
        Separator separator2 = new Separator();

        /** Панель с игровым полем*/
        gamePanel = new Pane();
        // Создаем сетку
        GridPane map = new GridPane();
        map.setGridLinesVisible(true);
        int cellSize = 70;
        for(int i = 0; i < map_size;i++) {
            map.getColumnConstraints().add(new ColumnConstraints(cellSize));
            map.getRowConstraints().add(new RowConstraints(cellSize));
            for (int j = 0; j < map_size; j++) {
                Label cell = new Label("CELL" + i + j);
                cell.setAlignment(Pos.CENTER_RIGHT);
                map.add(cell, i, j);
            }
        }
        map.setLayoutX(40);
        gamePanel.getChildren().addAll(map);

        /** Группируем основные панели */
        root.getChildren().addAll(settingsPanel,separator1, separator2, gamePanel);
//        this.map = new Label[map_size][map_size];
//        for(int i = 0;i < map_size;i++)
//            for(int j = 0; j < map_size; j++)
//            {
//                map[i][j] = new Label("CELL" + i + j);
//            }

        primaryStage.setTitle("Tick Tack");
        primaryStage.setScene(new Scene(root, 300, 340));
        primaryStage.show();
    }
}
