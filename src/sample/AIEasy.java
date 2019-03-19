package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIEasy extends Player {
    protected Controller.Play map;

    public AIEasy(String name, Cell.State state, Controller.Play gamePlay) {
        super(name, state);
        this.map = gamePlay;
    }

    @Override
    public void setCanStep(boolean canStep) {
        super.setCanStep(canStep);
        if(canStep)
            step();
    }
    protected void step()
    {
        List<Cell> freeCells = new ArrayList<>();
        for(Cell row[] : map.getMap())
            for(Cell cell : row)
                if(cell.getCurrentState() == Cell.State.EMPTY)
                    freeCells.add(cell);

        Random random = new Random();
        map.click(freeCells.get(random.nextInt(freeCells.size())));
    }



}
