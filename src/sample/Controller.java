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
        if(cell.setState(currentPlayer.getState())) {
            update();
        }
    }
    public void update()
    {
        if(currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
        viewController.getCurrentPlayer().setText(currentPlayer.toString());
    }

}
