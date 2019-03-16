package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController {


    private Controller controller;
    private VBox root;
    private HBox settingsPanel;
    private Pane gamePanel;
    private GameMap gameMap;

    ViewController(Stage primaryStage) throws IOException {
        this.controller = controller;

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
        gameMap = new GameMap(3);
        gameMap.setLayoutX(40);
        gamePanel.getChildren().addAll(gameMap);

        /** Группируем основные панели */
        root.getChildren().addAll(settingsPanel,separator1, separator2, gamePanel);

        primaryStage.setTitle("Tick Tack");
        primaryStage.setScene(new Scene(root, 300, 340));
        primaryStage.show();
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
