package sample;

public class TestMap extends GameMap {
    public TestMap(int size) {
        super(size);
    }

    @Override
    public void start() {
        super.start();
        map[0][0].setState(Cell.State.O);
        map[0][2].setState(Cell.State.X);
        map[1][0].setState(Cell.State.X);
        map[1][2].setState(Cell.State.X);
        map[2][1].setState(Cell.State.O);
        map[2][2].setState(Cell.State.O);
    }
}
