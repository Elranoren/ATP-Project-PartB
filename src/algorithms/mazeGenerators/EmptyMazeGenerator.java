package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    /**
     * @param rows Maze rows
     * @param columns Maze columns
     * @return an Empty Maze with size of rows/columns.
     */
    @Override
    public Maze generate(int rows, int columns) {
        Maze m = new Maze(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                m.getMaze()[i][j] = 0;
            }
        }
        return m;
    }
}
