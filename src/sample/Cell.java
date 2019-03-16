package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Cell extends Pane {
        public final int i, j;
        private X x;
        private O o;
        private State currentState = State.EMPTY;

        enum State
        {
            EMPTY, X, O
        }

        Cell(int i, int j, int cellSize)
        {
            this.i = i;
            this.j = j;

            this.x = new X(cellSize);
            this.getChildren().add(x);
            this.x.setVisible(false);

            this.o = new O(cellSize);
            this.getChildren().add(o);
            this.o.setVisible(false);
        }
        public boolean setState(State state)
        {
            if(currentState != State.EMPTY)
                return false;
            currentState = state;
            update();
            return true;
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
