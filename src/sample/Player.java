package sample;

public class Player {
    private String name;
    private Cell.State play;
    private boolean canStep;

    public Player(String name, Cell.State play)
    {
        this.name = name;
        this.play = play;
    }

    public Cell.State getPlay() {
        return play;
    }

    public void setCanStep(boolean canStep) {
        this.canStep = canStep;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
