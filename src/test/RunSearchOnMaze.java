package test;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.*;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;
    public class RunSearchOnMaze {
        public static void main(String[] args) {
//            int[][] map = {{ 1, 0, 1, 0 ,0 ,0, 0, 0 ,0 ,0, 1, 0 ,0 ,0, 1 ,0 ,0, 0, 0 ,0 ,0, 0, 0 ,0 ,0, 0, 0, 0 ,1, },
//            { 1, 0 ,1, 0, 1, 1 ,1, 0 ,1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, },
//            { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, },
//            { 1, 0, 1, 0, 1, 0, 1 ,0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, },
//            { 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, },
//            { 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1 ,1 ,1 ,1 ,1 },
//            { 1 ,0 ,1 ,0 ,1 ,0 ,0 ,0, 0, 0 ,1 ,0 ,0 ,0 ,1, 0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 }
//            ,{ 1, 0, 1, 0 ,1 ,0 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, },
//            { 1, 0, 1, 0, 1, 0, 1, 0 ,1 ,0, 0, 0, 1, 0, 0, 0, 1, 0, 1 ,0 ,1 ,0, 0, 0, 0, 0, 1, 0, 1 },
//            { 1, 0, 1, 0, 1, 1, 1 ,0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,0 ,1 },
//            { 1 ,0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,0 ,0, 0, 0, 1 },
//            { 1, 0, 1 ,1 ,1 ,1 ,1, 1, 1, 0, 1 ,0 ,1 ,0 ,1 ,1, 1 ,1 ,1, 1, 1 ,1 ,1, 0,1, 1, 1, 0, 1 },
//            { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1 },
//            { 1, 0, 1, 1, 1, 1, 1, 1, 1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,1 ,0, 1, 0, 1 },
//            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 ,0 ,1 ,0 ,1 ,0 ,0 ,0, 1, 0, 0, 0, 1, 0 ,1 ,0 ,1 },
//            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1, 1, 0, 1, 0, 1, 0 ,1 ,0 ,1 },
//            { 1, 0, 0, 0, 1, 0 ,0 ,0 ,0 ,0 ,1 ,0, 0, 0, 0, 0, 1, 0, 1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0, 1 },
//            { 1 ,1 ,1 ,0, 1, 0 ,1, 1 ,1, 0 ,1 ,1 ,1 ,1 ,1 ,1 ,1, 0 ,1 ,0 ,1 ,1 ,0 ,1 ,1 ,0 ,1 ,0 ,1 },
//            { 1, 0 ,0 ,0 ,1 ,0, 1, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 ,1 ,0 ,0 ,0, 1 ,0 ,0 ,0 ,1 ,0 ,0 },
//            { 1, 0, 1 ,1 ,1 ,0 ,1 ,1 ,1, 1, 1, 1 ,1 ,1 ,1 ,0, 1, 0 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,1 ,1 ,0 ,1 },
//            { 1, 0, 1 ,0 ,0 ,0 ,1 ,0 ,0 ,0, 0 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,0 ,1 },
//            { 1, 0 ,1 ,0, 1 ,0 ,1, 0, 1, 0 ,1, 0 ,1 ,1 ,1 ,1 ,1 ,1 ,1, 0, 1, 0 ,1 ,1 ,1 ,1 ,1 ,0 ,1 },
//            { 1 ,0 ,1 ,0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0 ,0 ,0 ,0 ,0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1 },
//            { 1 ,0 ,1, 1, 1 ,0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0 ,1 ,1 ,1 ,1 ,1 ,0 ,1 ,0 ,1 ,0 ,1 },
//            { 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0 ,0 ,0 ,1, 0, 1, 0 ,1 ,0 ,0 ,0 ,1 ,0 ,1 ,0, 0 ,0 ,1 },
//            { 1 ,1 ,1 ,0 ,1 ,1 ,1 ,1 ,1 ,0 ,1 ,1, 1, 0, 1, 0 ,1 ,0, 1 ,0 ,1 ,0, 1, 0, 1 ,1 ,1 ,0 ,1 },
//            { 1 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 ,0 ,0 ,0 ,1 ,0 ,0 ,0, 1 ,0 ,0 ,0 ,0 ,0, 0, 0, 1 }};
//            Position start = new Position(0,13);
//            Position end = new Position(18,28);
//            int[][] map = {{0,0,0,0,0},{0,0,1,1,0},{1,0,0,1,0},{1,1,0,0,0}};
//            Position start = new Position(0,0);
//            Position end = new Position(2,4);
//            Maze maze = new Maze(start,end,map);
//            maze.setMaze(map);
//            maze.setStartPosition(start);
//            maze.setGoalPosition(end);
//            maze.print();
            IMazeGenerator mg = new MyMazeGenerator();
            Maze maze = mg.generate(-4, -5);
            maze.print();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new DepthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());
        }
        private static void solveProblem(ISearchable domain, ISearchingAlgorithm
                searcher) {
//Solve a searching problem with a searcher
            Solution solution = searcher.solve(domain);
            System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
                    System.out.println("Solution path:");
            ArrayList<AState> solutionPath = solution.getSolutionPath();
            for (int i = 0; i < solutionPath.size(); i++) {
                System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
            }
        }
    }

