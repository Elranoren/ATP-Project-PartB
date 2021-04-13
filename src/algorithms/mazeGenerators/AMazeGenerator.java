package algorithms.mazeGenerators;
import java.lang.System;

public abstract class  AMazeGenerator implements IMazeGenerator {
    /**
     * @param rows Maze rows
     * @param columns Maze columns
     * @return Maze
     */
    public abstract Maze generate(int rows, int columns) throws Exception;

    /**
     * @param rows Maze rows
     * @param columns Maze columns
     * @return long , time complexity of the function generate.
     */
    public long measureAlgorithmTimeMillis(int rows , int columns){
        long start = System.currentTimeMillis();
        try {
            generate(rows,columns);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
