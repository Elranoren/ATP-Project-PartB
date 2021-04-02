package algorithms.mazeGenerators;
import algorithms.search.*;

import java.util.List;

public class Maze{
    private int rows , columns;
    private Position start,end;
    private int[][] maze;

    /**
     * @param rows Maze rows
     * @param columns Maze columns
     */
    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        maze = new int[rows][columns];

        }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public Position getStartPosition() {
        return this.start;
    }

    public Position getGoalPosition() {
        return this.end;
    }

    public void setStartPosition(Position p) {
        this.start = p;
    }

    public void setGoalPosition(Position p) {
        this.end = p;
    }

    public void print() {

        System.out.print("{ ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
             boolean isStart = i == start.getRowIndex() && j==start.getColumnIndex();
             boolean isEnd = i == end.getRowIndex() && j==end.getColumnIndex();
             if(isStart){
                 System.out.print("S ");
             }
             else if(isEnd){
                 System.out.print("E ");
             }
             else if (maze[i][j]==0){
                 System.out.print("0 ");
             }
             else
                 System.out.print("1 ");
             if(j==columns-1){
                 System.out.print("}");
                 if(i!=rows-1){
                 System.out.print("\n");
                 System.out.print("{ ");
                 }
             }
            }

        }

    }


}

