package algorithms.maze3D;

import algorithms.mazeGenerators.IMazeGenerator;

public interface IMazeGenerator3D{
    Maze3D generate(int depth, int row, int column);
    long measureAlgorithmTimeMillis(int depth, int row, int column);

}
