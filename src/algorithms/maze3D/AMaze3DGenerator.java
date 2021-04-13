package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    /**
     * @param depth the depth of the maze
     * @param row the row of the maze
     * @param column the column of the maze
     * @return how much time the generate maze takes
     */
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long start = System.currentTimeMillis();
        try {
            Maze3D m = generate(depth,row,column);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
