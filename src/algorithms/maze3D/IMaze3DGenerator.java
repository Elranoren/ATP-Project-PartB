package algorithms.maze3D;

import algorithms.mazeGenerators.IMazeGenerator;

public interface IMaze3DGenerator {
    Maze3D generate(int depth, int row, int column) throws Exception;
    long measureAlgorithmTimeMillis(int depth, int row, int column);

}
