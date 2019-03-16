package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Controller {
    private ViewController viewController;
    private Player player1;
    private Player player2;

    public Controller(ViewController viewController)
    {
        player1 = new Player(Cell.State.X);
        player2 = new Player(Cell.State.O);

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
        cell.setState(player1.getState());
    }

}
