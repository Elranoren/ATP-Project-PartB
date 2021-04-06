package algorithms.maze3D;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Maze3D {

    private Position3D start, end;
    private int[][][] maze3D;

    /**
     * @param rows    Maze rows
     * @param columns Maze columns
     */
    public Maze3D(Position3D start, Position3D end, int[][][] map) {
        maze3D = map;
        this.start = start;
        this.end = end;

    }


    public int[][][] getMap() {
        return maze3D;
    }

    public int getRows() {
        return this.maze3D[0].length;
    }

    public int getColumns() {
        return this.maze3D[0][0].length;
    }

    public int getDepth() {
        return this.maze3D.length;
    }

    public void setMaze(int[][][] maze3D) {
        this.maze3D = maze3D;
    }

    public Position3D getStartPosition() {
        return this.start;
    }

    public Position3D getGoalPosition() {
        return this.end;
    }

    public void setStartPosition(Position3D p) {
        this.start = p;
    }

    public void setGoalPosition(Position3D p) {
        this.end = p;
    }

    public void print() {
        System.out.print("{ ");
        System.out.print("\n");

        for (int k = 0; k < getDepth(); k++) {
            System.out.print("{ ");
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {

                    boolean isStart = i == start.getRowIndex() && j == start.getColumnIndex() && k == start.getDepthIndex();
                    boolean isEnd = i == end.getRowIndex() && j == end.getColumnIndex() && k == end.getDepthIndex();
                    if (isStart) {
                        System.out.print("S ");
                    } else if (isEnd) {
                        System.out.print("E ");
                    } else if (maze3D[k][i][j] == 0) {
                        System.out.print("0 ");
                    } else
                        System.out.print("1 ");
                    if (j == getColumns() - 1) {
                        System.out.print("}");
                        if (i != getRows() - 1) {
                            System.out.print("\n");
                            System.out.print("{ ");
                        }
                    }
                }


            }

            System.out.print("\n");

            System.out.print(IntStream.range(0,( (getColumns()+2)*2 )-1).mapToObj(i -> "-").collect(Collectors.joining("")));
            System.out.print("\n");




        }

        System.out.print("}");
    }
}
