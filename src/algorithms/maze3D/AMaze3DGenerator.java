package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long start = System.currentTimeMillis();
        Maze3D m = generate(depth,row,column);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
