package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle{
        public final int i, j;
        private State currentState = State.EMPTY;

        enum State
        {
            EMPTY, X, O
        }

        Cell(int i, int j,int x, int y, int width, int height)
        {
            super(x, y, width,height);
            this.i = i;
            this.j = j;

            this.setStroke(new Color(0,0,0,0));
        }
        public boolean setState(State state)
        {
            if(currentState != State.EMPTY)
                return false;
            currentState = state;
            update();
            return true;
        }
        public void update()
        {
            if(currentState == State.EMPTY)
                this.setFill(Color.BLACK);
            else if(currentState == State.X)
                this.setFill(Color.RED);
            else if(currentState == State.O)
                this.setFill(Color.BLUE);
        }

}
