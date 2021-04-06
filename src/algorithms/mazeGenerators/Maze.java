package algorithms.mazeGenerators;
import algorithms.search.*;

import java.util.List;

public class Maze{
    private Position start,end;
    private int[][] maze;

    /**
     * @param rows Maze rows
     * @param columns Maze columns
     */
    public Maze(Position start , Position end , int [][] map) {

        maze = map;
        this.start = start;
        this.end = end;

        }

    public int getRows() {
        return this.maze.length;
    }

    public int getColumns() {
        return this.maze[0].length;
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
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
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
             if(j==getColumns()-1){
                 System.out.print("}");
                 if(i!=getRows()-1){
                 System.out.print("\n");
                 System.out.print("{ ");
                 }
             }
            }

        }

    }


}

