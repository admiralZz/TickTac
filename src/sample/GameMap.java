package sample;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

class GameMap extends GridPane
{
    // коэффиецент размера клетки к количеству клеток
    private static final float K_CELL_SIZE = 70.0f/3.0f;

    Cell map[][];

    public GameMap(int size)
    {
        map = new Cell[size][size];
        this.setGridLinesVisible(true);
        int cellSize = Math.round(size * K_CELL_SIZE);
        for(int i = 0; i < size;i++) {
            this.getColumnConstraints().add(new ColumnConstraints(cellSize));
            this.getRowConstraints().add(new RowConstraints(cellSize));

            for(int j = 0; j < size; j++)
            {
                Cell cell = new Cell(i, j, cellSize);
                map[i][j] = cell;
                this.add(cell,j,i);

            }
        }
        //start();
    }
    public void start()
    {
        for(Cell row[] : map)
            for(Cell cell : row) {
                cell.clear();
                cell.update();
            }
    }
}