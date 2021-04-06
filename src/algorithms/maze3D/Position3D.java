package algorithms.maze3D;

import algorithms.search.AState;

public class Position3D extends AState {
    private int depth, row , column;

    public Position3D(int depth, int row, int column) {
        this.depth = depth;
        this.row = row;
        this.column = column;

    }

    public String toString() {
        return "{" + this.depth + "," + this.column + "," + this.row + "}";
    }

    @Override
    public String getStateName() {
        return this.toString();
    }

    public int getRowIndex() {
        return row;
    }

    public void setRowIndex(int row) {
        this.row = row;
    }

    public int getColumnIndex() {
        return column;
    }

    public void setColumnIndex(int column) {
        this.column = column;
    }

    public int getDepthIndex() {
        return depth;
    }

    public void setDepthIndex(int depth) {
        this.depth = depth;
    }
}
