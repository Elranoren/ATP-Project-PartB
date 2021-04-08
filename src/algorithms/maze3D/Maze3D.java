package algorithms.maze3D;

public class Maze3D {

    private Position3D startPosition, goalPosition;
    private int[][][] map;

    /**
     * @param startPosition The start position of the maze
     * @param goalPosition The end position of the maze
     * @param map The map
     */
    public Maze3D(Position3D startPosition, Position3D goalPosition, int[][][] map) {
        this.map = map;
        this.startPosition = startPosition;
        this.goalPosition = goalPosition;

    }


    public int[][][] getMap() {
        return map;
    }

    public int getRows() {
        return this.map[0].length;
    }

    public int getColumns() {
        return this.map[0][0].length;
    }

    public int getDepth() {
        return this.map.length;
    }

    public void setMaze(int[][][] maze3D) {
        this.map = maze3D;
    }

    public Position3D getStartPosition() {
        return this.startPosition;
    }

    public Position3D getGoalPosition() {
        return this.goalPosition;
    }

    public void setStartPosition(Position3D p) {
        this.startPosition = p;
    }

    public void setGoalPosition(Position3D p) {
        this.goalPosition = p;
    }

    /**
     * print the maze3D
     */
//    public void print() {
//        System.out.print("{ ");
//        System.out.print("\n");
//        for (int k = 0; k < getDepth(); k++) {
//            System.out.print("{ ");
//            for (int i = 0; i < getRows(); i++) {
//                for (int j = 0; j < getColumns(); j++) {
//
//                    boolean isStart = i == start.getRowIndex() && j == start.getColumnIndex() && k == start.getDepthIndex();
//                    boolean isEnd = i == end.getRowIndex() && j == end.getColumnIndex() && k == end.getDepthIndex();
//                    if (isStart) {
//                        System.out.print("S ");
//                    } else if (isEnd) {
//                        System.out.print("E ");
//                    } else if (maze3D[k][i][j] == 0) {
//                        System.out.print("0 ");
//                    } else
//                        System.out.print("1 ");
//                    if (j == getColumns() - 1) {
//                        System.out.print("}");
//                        if (i != getRows() - 1) {
//                            System.out.print("\n");
//                            System.out.print("{ ");
//                        }
//                    }
//                }
//            }
//            System.out.print("\n");
//            System.out.print(IntStream.range(0,( (getColumns()*2)+3)).mapToObj(i -> "-").collect(Collectors.joining("")));
//            System.out.print("\n");
//        }
//        System.out.print("}");
//        System.out.print("\n");
//    }
    public void print(){
        System.out.println("{");
        for(int depth = 0; depth < map.length; depth++){
            for(int row = 0; row < map[0].length; row++) {
                System.out.print("{ ");
                for (int col = 0; col < map[0][0].length; col++) {
                    if (depth == startPosition.getDepthIndex() && row == startPosition.getRowIndex() && col == startPosition.getColumnIndex()) // if the position is the start - mark with S
                        System.out.print("S ");
                    else {
                        if (depth == goalPosition.getDepthIndex() && row == goalPosition.getRowIndex() && col == goalPosition.getColumnIndex()) // if the position is the goal - mark with E
                            System.out.print("E ");
                        else
                            System.out.print(map[depth][row][col] + " ");
                    }
                }
                System.out.println("}");
            }
            if(depth < map.length - 1) {
                System.out.print("---");
                for (int i = 0; i < map[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }
}
