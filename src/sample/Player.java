package sample;

public class Player {
    private Cell.State state;

    public Player(Cell.State state)
    {
        this.state = state;
    }

    public Cell.State getState() {
        return state;
    }
}
