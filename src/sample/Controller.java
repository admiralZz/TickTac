package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Controller {
    private ViewController viewController;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Controller(ViewController viewController)
    {
        player1 = new Player("Player 1", Cell.State.X);
        player2 = new Player("Player 2", Cell.State.O);
        currentPlayer = player1;

        this.viewController = viewController;
        for(Cell row[] : viewController.getGameMap().map)
            for(Cell cell : row)
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        click(cell);
                    }
                });
    }
    public void click(Cell cell)
    {
        System.out.println(cell.i + "" + cell.j);
        if(cell.setState(currentPlayer.getState())) {
            update();
        }
    }
    public void update()
    {
        if(checkVictory(Cell.State.X) || checkVictory(Cell.State.O)){
            System.out.println(currentPlayer.toString() + " VICTORY!");
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
        for(Cell row[] : viewController.getGameMap().map) {
            boolean rowVictory = true;
            for (Cell cell : row) {
                if (state != cell.getCurrentState()) {
                    rowVictory = false;
                    break;
                }
            }
            if(rowVictory) return true;
        }
        for(int i = 0; i < viewController.getGameMap().map.length; i++)
        {
            boolean columnVictory = true;
            for(int j = 0; j < viewController.getGameMap().map.length; j++)
            {
                Cell cell = viewController.getGameMap().map[j][i];
                if(state != cell.getCurrentState()){
                    columnVictory = false;
                    break;
                }
            }
            if(columnVictory) return true;
        }
        boolean diagonalVictory1 = true;
        for(int i = viewController.getGameMap().map.length; --i >= 0;)
        {
            Cell cell = viewController.getGameMap().map[viewController.getGameMap().map.length - 1 - i][i];
            if(state != cell.getCurrentState())
            {
                diagonalVictory1 = false;
                break;
            }
        }
        if(diagonalVictory1) return true;

        boolean diagonalVictory2 = true;
        for(int i = 0; i < viewController.getGameMap().map.length; i++)
        {
            Cell cell = viewController.getGameMap().map[i][i];
            if(state != cell.getCurrentState())
            {
                diagonalVictory2 = false;
                break;
            }
        }
        if(diagonalVictory2) return true;

        return false;
    }

}
