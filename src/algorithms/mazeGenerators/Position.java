package algorithms.mazeGenerators;
import java.io.Serializable;

public class Position {
    int row,column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean equal(Object o){
        if (this == o)
            return true;
        if((o instanceof Position)==false)
            return false;
        Position p = (Position) o;
        if(this.row!=(p.row))
            return false;
        return  p.column==this.column;



    }


    public int getRowIndex() {
        return row;
    }

    public void setRowindex(int row) {
        this.row = row;
    }

    public int getColumnIndex() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "{" + this.row + "," + this.column + "}";
    }
}
