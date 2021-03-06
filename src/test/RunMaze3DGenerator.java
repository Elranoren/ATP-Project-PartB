package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;

public class RunMaze3DGenerator {
    public static void main(String[] args) {
        //testMazeGenerator(new EmptyMazeGenerator());
        //testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMaze3DGenerator()); {
        };
    }
    private static void testMazeGenerator(IMaze3DGenerator mazeGenerator3D) {
// prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generationtime(ms): %s", mazeGenerator3D.measureAlgorithmTimeMillis(100,100,100)));
// generate another maze
        Maze3D maze = null;
        try {
            maze = mazeGenerator3D.generate(50,50,50);
        } catch (Exception e) {
            e.printStackTrace();
        }
// prints the maze
        maze.print();
// get the maze entrance
        Position3D startPosition = maze.getStartPosition();
// print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
// prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}