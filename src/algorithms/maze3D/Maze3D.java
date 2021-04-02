package algorithms.maze3D;


public class Maze3D {

    private int rows , columns, depth;
    private Position3D start,end;
    private int[][][] maze3D;

    /**
     * @param rows Maze rows
     * @param columns Maze columns
     */
    public Maze3D(int rows, int columns, int depth) {
        this.rows = rows;
        this.columns = columns;
        this.depth =depth;
        maze3D = new int[depth][rows][columns];

    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }


    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int[][][] getMap() {
        return maze3D;
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
        }




}
