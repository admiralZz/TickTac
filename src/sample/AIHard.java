package sample;

import java.util.ArrayList;
import java.util.List;

public class AIHard extends AIEasy {

    private Cell.State aiPlayer;
    private Cell.State huPlayer;
    public AIHard(String name, Controller.Play gamePlay)
    {
        super(name, gamePlay);
    }

    public AIHard(String name, Cell.State state, Controller.Play gamePlay)
    {
        super(name, state, gamePlay);

        aiPlayer = state;
        if(aiPlayer == Cell.State.X)
            huPlayer = Cell.State.O;
        else
            huPlayer = Cell.State.X;
    }

    @Override
    public void setPlay(Cell.State play) {
        super.setPlay(play);

        aiPlayer = play;
        if(aiPlayer == Cell.State.X)
            huPlayer = Cell.State.O;
        else
            huPlayer = Cell.State.X;
    }

    @Override
    protected void step() {

        AiCell newMap[][] = createAiMap(map.getMap());
        Move move = minimax(newMap, aiPlayer);
        map.click(map.getMap()[move.cell.i][move.cell.j]);

    }
    private Move minimax(AiCell map[][], Cell.State player)
    {
        // Ходы
        List<Move> moves = new ArrayList<>();

        // Проверка на победу, поражение или ничью
        if(checkVictory(map, aiPlayer))
            return new Move(null, 10);
        else if(checkVictory(map, huPlayer))
            return new Move(null, -10);
        else if(draw(map))
            return new Move(null, 0);

        // Доступные клетки
        List<AiCell> freeCells = checkFreeCells(map);

        for(AiCell freeCell : freeCells)
        {
            Move move = new Move(freeCell,0);
            AiCell fixCell = new AiCell(freeCell.i,freeCell.j,freeCell.state);

            // Совершить ход за текущего игрока
            freeCell.state = player;

            if(player == aiPlayer)
            {
                Move result = minimax(map, huPlayer);
                move.score = result.score;
            }
            else
            {
                Move result = minimax(map, aiPlayer);
                move.score = result.score;
            }

            freeCell.state = fixCell.state;
            moves.add(move);
        }

        Move bestMove = null;
        if(player == aiPlayer)
        {
            int bestScore = -10000;
            for(Move move : moves)
            {
                if(move.score > bestScore) {
                    bestScore = move.score;
                    bestMove = move;
                }
            }
        }
        else
        {
            int bestScore = 10000;
            for(Move move : moves)
            {
                if(move.score < bestScore) {
                    bestScore = move.score;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

    private List<AiCell> checkFreeCells(AiCell map[][])
    {
        List<AiCell> freeCells = new ArrayList<>();
        for(AiCell row[] : map)
            for(AiCell cell : row)
            {
                if(cell.state == Cell.State.EMPTY)
                    freeCells.add(cell);
            }
        return freeCells;
    }
    private AiCell[][] createAiMap(Cell map[][])
    {
        AiCell newMap[][] = new AiCell[map.length][map.length];
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map.length; j++)
            {
                Cell cell = map[i][j];
                AiCell newCell = new AiCell(cell.row, cell.column, cell.getCurrentState());
                newMap[i][j] = newCell;
            }
        }
        return newMap;
    }
    private boolean checkVictory(AiCell map[][], Cell.State state)
    {

        for(AiCell row[] : map) {
            boolean rowVictory = true;
            for (AiCell cell : row) {
                if (state != cell.state) {
                    rowVictory = false;
                    break;
                }
            }
            if(rowVictory)
                return true;

        }
        for(int i = 0; i < map.length; i++)
        {
            boolean columnVictory = true;
            for(int j = 0; j < map.length; j++)
            {
                AiCell cell = map[j][i];
                if(state != cell.state){
                    columnVictory = false;
                    break;
                }
            }
            if(columnVictory)
                return true;
        }
        boolean diagonalVictory1 = true;
        for(int i = map.length; --i >= 0;)
        {
            AiCell cell = map[map.length - 1 - i][i];
            if(state != cell.state) {
                diagonalVictory1 = false;
                break;
            }
        }
        if(diagonalVictory1)
            return true;


        boolean diagonalVictory2 = true;
        for(int i = 0; i < map.length; i++)
        {
            AiCell cell = map[i][i];
            if(state != cell.state) {
                diagonalVictory2 = false;
                break;
            }
        }
        if(diagonalVictory2)
            return true;

        return false;
    }
    private boolean draw(AiCell map[][])
    {
        for(AiCell row[] : map) {
            for (AiCell cell : row) {
                if (cell.state == Cell.State.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    private class AiCell{
        int i;
        int j;
        Cell.State state;

        AiCell(int i, int j, Cell.State state)
        {
            this.i = i;
            this.j = j;
            this.state = state;
        }

    }
    private class Move
    {
        AiCell cell;
        int score;

        Move(AiCell cell, int score)
        {
            this.cell = cell;
            this.score = score;

        }

    }
}
