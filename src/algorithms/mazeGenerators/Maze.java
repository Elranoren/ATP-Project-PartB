package algorithms.mazeGenerators;

public class Maze{
    private Position start,end;
    private int[][] maze;
    static int index=0;

    /**
     * @param start start position of the maze
     * @param end end position of th maze
     * @param map the maze
     */
    public Maze(Position start , Position end , int [][] map) {

        maze = map;
        this.start = start;
        this.end = end;

        }

    public Maze(byte[] mazeByteBuffer) {
        index=0;
        int sRow = byteTointMaze(mazeByteBuffer);
        int sCol= byteTointMaze(mazeByteBuffer);
        int eRow= byteTointMaze(mazeByteBuffer);
        int eCol= byteTointMaze(mazeByteBuffer);
        int bRow= byteTointMaze(mazeByteBuffer);
        int bCol= byteTointMaze(mazeByteBuffer);
        setStartPosition(new Position(sRow,sCol));
        setGoalPosition(new Position(eRow,eCol));
        setMaze(new int[bRow][bCol]);
        for (int i = 0; i < bRow; i++) {
            for (int j = 0; j < bCol; j++) {
                getMaze()[i][j] = mazeByteBuffer[index];
                index++;
            }
        }
//        index=0;
//
//        setStartPosition(new Position(byteTointMaze(mazeByteBuffer),byteTointMaze(mazeByteBuffer)));
//        setGoalPosition(new Position(byteTointMaze(mazeByteBuffer),byteTointMaze(mazeByteBuffer)));
//        int bRow= byteTointMaze(mazeByteBuffer);
//        int bCol= byteTointMaze(mazeByteBuffer);
//        setMaze(new int[bRow][bCol]);
//        for (int i = 0; i < bRow; i++) {
//            for (int j = 0; j < bCol; j++) {
//                getMaze()[i][j] = mazeByteBuffer[index];
//                index++;
//            }
//        }

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
    public byte[] toByteArray() {
        index=0;
        int size = getColumns()*getRows();
        int sRowSize = intToByte(getStartPosition().getRowIndex());
        int sColSize = intToByte(getStartPosition().getColumnIndex());
        int eRowSize = intToByte(getGoalPosition().getRowIndex());
        int eColSize = intToByte(getGoalPosition().getColumnIndex());
        int rowSize = intToByte(getRows());
        int colSize = intToByte(getColumns());
        byte[] mazeByteBuffer = new byte[6+size+sRowSize+sColSize+eRowSize+eColSize+rowSize+colSize];
        intTobyteBuffer(getStartPosition().getRowIndex(),mazeByteBuffer);
        intTobyteBuffer(getStartPosition().getColumnIndex(),mazeByteBuffer);
        intTobyteBuffer(getGoalPosition().getRowIndex(),mazeByteBuffer);
        intTobyteBuffer(getGoalPosition().getColumnIndex(),mazeByteBuffer);
        intTobyteBuffer(getRows(),mazeByteBuffer);
        intTobyteBuffer(getColumns(),mazeByteBuffer);

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                mazeByteBuffer[index] = (byte) getMaze()[i][j];
                index++;

            }

        }
        return mazeByteBuffer;
    }
    public int intToByte(int num){
        int c = 0;
        if(num>255){
            while(num>255) {
                c++;
                num -= 255;
            }
        }
        return c+1;
    }

    public byte[] intTobyteBuffer(int num , byte[] buffer){
        if(num>255) {
            while (num > 255) {
                buffer[index] = (byte) 255;
                index++;
                num -= 255;
            }
        }
        buffer[index] = (byte) num;
        index++;
        buffer[index] = (byte) 0;
        index++;
        return buffer;
    }
    public int byteTointMaze(byte[] buffer){
        int num = 0;
        if(buffer[index]==0)
            index++;
        while(buffer[index]==-1){
            num+=255;
            index++;
        }
        if(buffer[index]<0){
            num += buffer[index]+256;
            index++;
        }
        if(buffer[index]>0){
            num += buffer[index];
            index++;
        }
        if(buffer[index]==0)
            index++;
        return num;

    }
    /**
     * prints the maze
     */
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
        System.out.print("\n");
    }



}

