package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController {

    public final Button restart;

    private Controller controller;
    private VBox root;
    private HBox settingsPanel;
    private Pane gamePanel;
    private GameMap gameMap;
    private Label currentPlayer;

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
        BorderPane buttonPanel = new BorderPane();
        restart = new Button("restart");
        restart.setVisible(false);
        buttonPanel.setLeft(restart);
        settingsPanel.getChildren().addAll(modePanel, buttonPanel);

        /** Разделители */
        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        Separator separator3 = new Separator();
        Separator separator4 = new Separator();

        /** Панель с игровым полем*/
        gamePanel = new Pane();
        VBox.setMargin(gamePanel,new Insets(20,0,20,0));
        gameMap = new GameMap(3);
        gameMap.setLayoutX(40);
        gamePanel.getChildren().addAll(gameMap);

        /** Инфо панель */
        BorderPane infoPane = new BorderPane();
        Label label = new Label("Current player: ");
        currentPlayer = new Label("Player 1");
        infoPane.setLeft(label);
        infoPane.setRight(currentPlayer);

        /** Группируем основные панели */
        root.getChildren().addAll(settingsPanel,separator1, separator2, gamePanel, separator3, separator4, infoPane);

        primaryStage.setTitle("Tick Tack");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

    public GameMap getGameMap() {
        return gameMap;
    }
    public Label getCurrentPlayer()
    {
        return currentPlayer;
    }
}
