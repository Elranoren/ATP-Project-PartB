package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    int row,column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @param o an object value to check if equal to this position
     * @return true if equal, else: return false
     */
    public boolean equals(Object o){
        if (this == o)
            return true;
        if((o instanceof Position)==false)
            return false;
        Position p = (Position) o;
        if(this.row!=(p.row))
            return false;
        return  p.column==this.column;
    }


    /**
     * @param p a position value to check if equal to this position
     * @return true if equal, else: return false
     */
    public boolean equals(Position p){
        if(this.row!=(p.row))
            return false;
        return  p.column==this.column;
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

    public void setPosition(Position pos) {
        setRowIndex(pos.getRowIndex());
        setColumnIndex(pos.getColumnIndex());
    }
    @Override
    public String toString() {
        return "{" + this.row + "," + this.column + "}";
    }


    public String getStateName() {
        return this.toString();
    }
}
