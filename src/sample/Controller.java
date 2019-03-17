package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private ViewController viewController;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean end;

    public Controller(ViewController viewController)
    {
        this.viewController = viewController;
        for(Cell row[] : viewController.getGameMap().map)
            for(Cell cell : row)
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        click(cell);
                    }
                });
        this.viewController.restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start();
            }
        });
        start();
    }
    public void start()
    {
        player1 = new Player("Player 1", Cell.State.X);
        player2 = new Player("Player 2", Cell.State.O);
        currentPlayer = player1;
        viewController.getGameMap().start();

        end = false;
        viewController.restart.setVisible(end);
    }
    public void click(Cell cell)
    {
        if(!end) {
            if (cell.setState(currentPlayer.getState())) {
                update();
            }
        }
    }
    public void update()
    {
        if(checkVictory(Cell.State.X) || checkVictory(Cell.State.O) || draw()){
            end = true;
            viewController.restart.setVisible(end);
        }
        else {
            if (currentPlayer == player1)
                currentPlayer = player2;
            else
                currentPlayer = player1;
            viewController.getCurrentPlayer().setText(currentPlayer.toString());
        }
    }
    public boolean checkVictory(Cell.State state)
    {
        List<Cell> victoryLine = new ArrayList<>();

        for(Cell row[] : viewController.getGameMap().map) {
            boolean rowVictory = true;
            for (Cell cell : row) {
                victoryLine.add(cell);
                if (state != cell.getCurrentState()) {
                    rowVictory = false;
                    victoryLine.clear();
                    break;
                }
            }
            if(rowVictory) {
                for(Cell cell : victoryLine)
                    cell.setHline(true);
                return true;
            }
        }
        for(int i = 0; i < viewController.getGameMap().map.length; i++)
        {
            boolean columnVictory = true;
            for(int j = 0; j < viewController.getGameMap().map.length; j++)
            {
                Cell cell = viewController.getGameMap().map[j][i];
                victoryLine.add(cell);
                if(state != cell.getCurrentState()){
                    columnVictory = false;
                    victoryLine.clear();
                    break;
                }
            }
            if(columnVictory) {
                for(Cell cell : victoryLine)
                    cell.setVline(true);
                return true;
            }
        }
        boolean diagonalVictory1 = true;
        for(int i = viewController.getGameMap().map.length; --i >= 0;)
        {
            Cell cell = viewController.getGameMap().map[viewController.getGameMap().map.length - 1 - i][i];
            victoryLine.add(cell);
            if(state != cell.getCurrentState()) {
                diagonalVictory1 = false;
                victoryLine.clear();
                break;
            }
        }
        if(diagonalVictory1) {
            for(Cell cell : victoryLine)
                cell.setDline1(true);
            return true;
        }

        boolean diagonalVictory2 = true;
        for(int i = 0; i < viewController.getGameMap().map.length; i++)
        {
            Cell cell = viewController.getGameMap().map[i][i];
            victoryLine.add(cell);
            if(state != cell.getCurrentState()) {
                diagonalVictory2 = false;
                victoryLine.clear();
                break;
            }
        }
        if(diagonalVictory2) {
            for(Cell cell : victoryLine)
                cell.setDline2(true);
            return true;
        }

        return false;
    }
    private boolean draw()
    {
        for(Cell row[] : viewController.getGameMap().map) {
            for (Cell cell : row) {
                if (cell.getCurrentState() == Cell.State.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

}
