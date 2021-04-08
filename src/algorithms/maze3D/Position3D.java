package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

public class Position3D{
    private int depth, row , column;

    public Position3D(int depth, int row, int column) {
        this.depth = depth;
        this.row = row;
        this.column = column;

    }

    public String toString() {
        return "{" + this.depth + "," + this.row  + "," + this.column + "}";
    }

    public int getRowIndex() {
        return row;
    }

    /**
     * @param o an object to check if o equal to this
     * @return true if equal, false if not equal
     */
    public boolean equals(Object o){
        if (this == o)
            return true;
        if((o instanceof Position3D)==false)
            return false;
        Position3D p = (Position3D) o;
        if(this.row!=(p.row))
            return false;
        if(this.depth!=(p.depth))
            return false;
        return  p.column==this.column;
    }

    public int getColumnIndex() {
        return column;
    }

    public int getDepthIndex() {
        return depth;
    }

}
