package sample;

public class Player {
    private String name;
    private Cell.State state;

    public Player(String name, Cell.State state)
    {
        this.name = name;
        this.state = state;
    }

    public Cell.State getState() {
        return state;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
