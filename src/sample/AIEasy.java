package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIEasy extends Player {
    private Controller.Played gamePlay;

    public AIEasy(String name, Cell.State state, Controller.Played gamePlay) {
        super(name, state);
        this.gamePlay = gamePlay;
    }

    @Override
    public void setCanStep(boolean canStep) {
        super.setCanStep(canStep);
        if(canStep)
        {
            List<Cell> freeCells = new ArrayList<>();
            for(Cell row[] : gamePlay.getMap())
                for(Cell cell : row)
                    if(cell.getCurrentState() == Cell.State.EMPTY)
                        freeCells.add(cell);

            Random random = new Random();
            gamePlay.click(freeCells.get(random.nextInt(freeCells.size())));

        }
    }



}
