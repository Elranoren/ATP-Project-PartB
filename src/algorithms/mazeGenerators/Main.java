package algorithms.mazeGenerators; /**
 * Created by Aviad on 5/10/2017.
 */

import algorithms.mazeGenerators.*;


import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static String m_resultsFileName = "results.txt";

    public static enum TestStatus {
        Passed, Failed
    }

    public static void main(String[] args) {
        appendToResultsFile("Test started!");
        Tests_GenerateMaze();
        appendToResultsFile("Test finished!");
    }

    private static String getTestStatusString(boolean testPassed) {
        return testPassed ? TestStatus.Passed.toString() : TestStatus.Failed.toString();
    }

    private static int[][] getRowsColumnsCombinations() {
        int[][] rowsColumnsCombinations = {
                {1000, 1000}
        };
        return rowsColumnsCombinations;
    }

    //<editor-fold desc="Tests_GenerateMaze">
    private static void Tests_GenerateMaze() {
        int[][] rowsColumnsCombinations = getRowsColumnsCombinations();

        int rows;
        int columns;
        for (int i = 0; i < rowsColumnsCombinations.length; i++) {
            rows = rowsColumnsCombinations[i][0];
            columns = rowsColumnsCombinations[i][1];
            Test_MazeGenerator(new MyMazeGenerator(), rows, columns);
        }
    }

    private static void Test_MazeGenerator(IMazeGenerator mazeGenerator, int rows, int columns) {
        boolean testStatus = true;
        try {
            // prints the time it takes the algorithm to run
            mazeGenerator.measureAlgorithmTimeMillis(rows, columns);
            // generate another maze
            Maze maze = mazeGenerator.generate(rows, columns);
            // prints the maze
            maze.print();

            // get the maze entrance
            Position startPosition = maze.getStartPosition();
            startPosition.getColumnIndex();
            startPosition.getRowIndex();

            // get goal position
            Position startGoalPosition = maze.getGoalPosition();
            startPosition.getColumnIndex();
            startPosition.getRowIndex();
        } catch (Exception e) {
            testStatus = false;
        } finally {
            appendToResultsFile(String.format("TEST %s: generating maze (%s*%s) using %s", getTestStatusString(testStatus), rows, columns, mazeGenerator.getClass().getSimpleName().toString()));
        }
    }
    //</editor-fold>

    //<editor-fold desc="Tests_SearchOnMaze">

    //</editor-fold>

    public static void appendToResultsFile(String text) {
        try (java.io.FileWriter fw = new java.io.FileWriter(m_resultsFileName, true)) {
            fw.write(text + "\r\n");
        } catch (IOException ex) {
            System.out.println(String.format("Error appending text to file: %s", m_resultsFileName));
        }
    }
}
