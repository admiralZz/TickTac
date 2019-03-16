package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Cell extends Pane {
    private Line vline;
    private Line hline;
    private Line dline1;
    private Line dline2;

    public final int row, column;
    private X x;
    private O o;
    private State currentState = State.EMPTY;

    enum State
    {
        EMPTY, X, O
    }

    Cell(int i, int j, int cellSize)
    {
        this.row = i;
        this.column = j;

        this.x = new X(cellSize);
        this.getChildren().add(x);
        this.x.setVisible(false);

        this.o = new O(cellSize);
        this.getChildren().add(o);
        this.o.setVisible(false);

        int strokeWidth = 7;
        this.vline = new Line(cellSize/2, 0, cellSize/2, cellSize);
        this.hline = new Line(0, cellSize/2, cellSize, cellSize/2);
        this.dline1 = new Line(0,cellSize,cellSize,0);
        this.dline2 = new Line(0, 0, cellSize,cellSize);
        this.vline.setStroke(Color.RED);
        this.hline.setStroke(Color.RED);
        this.dline1.setStroke(Color.RED);
        this.dline2.setStroke(Color.RED);
        this.vline.setStrokeWidth(strokeWidth);
        this.hline.setStrokeWidth(strokeWidth);
        this.dline1.setStrokeWidth(strokeWidth);
        this.dline2.setStrokeWidth(strokeWidth);
        this.vline.setVisible(false);
        this.hline.setVisible(false);
        this.dline1.setVisible(false);
        this.dline2.setVisible(false);
        this.getChildren().addAll(vline,hline,dline1,dline2);
    }


    public State getCurrentState() {
        return currentState;
    }
    public boolean setState(State state)
    {
        if(currentState != State.EMPTY)
            return false;
        currentState = state;
        update();
        return true;
    }
    public void setVline(boolean setting)
    {
        this.vline.setVisible(setting);
    }
    public void setHline(boolean setting)
    {
        this.hline.setVisible(setting);
    }
    public void setDline1(boolean setting)
    {
        this.dline1.setVisible(setting);
    }
    public void setDline2(boolean setting)
    {
        this.dline2.setVisible(setting);
    }
    public void update() {
        if(currentState == State.EMPTY)
        {
            x.setVisible(false);
            o.setVisible(false);
        }
        if(currentState == State.X)
            x.setVisible(true);
        else if(currentState == State.O)
            o.setVisible(true);
    }

    class X extends Pane
    {
        Line line1 = new Line();
        Line line2 = new Line();
        public X(int size)
        {
            line1.setStartX(0);
            line1.setStartY(0);
            line1.setEndX(size);
            line1.setEndY(size);

            line2.setStartX(0);
            line2.setStartY(size);
            line2.setEndX(size);
            line2.setEndY(0);

            line1.setStrokeWidth(3);
            line2.setStrokeWidth(3);
            this.getChildren().addAll(line1,line2);

        }
    }
    class O extends Pane
    {
        Circle circle;
        public O(int size)
        {
            circle = new Circle();
            circle.setCenterX(size/2);
            circle.setCenterY(size/2);
            circle.setRadius(size/2);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(3);
            this.getChildren().add(circle);
        }
    }
}
