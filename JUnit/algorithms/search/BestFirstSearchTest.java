package algorithms.search;

import algorithms.maze3D.*;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BestFirstSearchTest {
    private BestFirstSearch bestfs = new BestFirstSearch();
    @Test
    void  TimeCheckTest() {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = null;
        try {
            maze = mg.generate(1000, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SearchableMaze sm = new SearchableMaze(maze);
        assertTrue(bestfs.measureAlgorithmTimeMillisOnSearchingAlgorithm(sm)<=60000);

    }
    @Test
    void  TimeCheckTest3D(){
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = null;
        try {
            maze = mg.generate(100, 100,100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SearchableMaze3D sm = new SearchableMaze3D(maze);
        assertTrue(bestfs.measureAlgorithmTimeMillisOnSearchingAlgorithm(sm)<=60000);

    }
    @Test
    void CheapestPathCheckVSBFS(){

        IMazeGenerator mg = new MyMazeGenerator();
        Maze m = null;
        Solution solutionBest = null;
        Solution solutionBFS = null;
        double costBest=0;
        double costBFS=0;
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        for (int i = 0; i < 15 ; i++) {
            try {
                m =  mg.generate(200,200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                solutionBest = bestfs.solve(new SearchableMaze(m));
                costBest= solutionBest.getSolutionPath().get(solutionBest.getSolutionPath().size()-1).getCost();
                solutionBFS = bfs.solve(new SearchableMaze(m));
                costBFS= solutionBFS.getSolutionPath().get(solutionBFS.getSolutionPath().size()-1).getCost();
                assertTrue(costBest<=costBFS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void  WrongParametersCheck(){
        IMazeGenerator mg = new MyMazeGenerator();
        Exception exception = assertThrows(Exception.class, () -> mg.generate(-19, -96));
        assertEquals(" Bad arguments - (rows < 2 or columns < 2) ", exception.getMessage());

    }

    @Test
    void  WrongParametersCheck3D(){
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Exception exception = assertThrows(Exception.class, () -> mg.generate(-40,-19, -96));
        assertEquals(" Bad arguments - (depth < 2 or row < 2 or column < 2) ", exception.getMessage());

    }

    @Test
    void  CheapestPathCheck(){
        ArrayList<AState> solcheck = new ArrayList<AState>();
        solcheck.add(new MazeState(new Position(0,0),null,"{0,0}"));
        solcheck.add(new MazeState(new Position(1,1),null,"{1,1}"));
        solcheck.add(new MazeState(new Position(2,2),null,"{2,2}"));
        int[][] map = {{0,1,1},{0,0,1},{0,0,0}};
        Position start = new Position(0,0);
        Position end = new Position(2,2);
        Maze maze = new Maze(start,end,map);
        maze.setMaze(map);
        maze.setStartPosition(start);
        maze.setGoalPosition(end);
        SearchableMaze sm = new SearchableMaze(maze);
        Solution s = null;
        try {
            s = bestfs.solve(sm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<AState> sp = s.getSolutionPath();
        boolean flag = true;
        for (int i = 0; i < sp.size(); i++) {
            if(!sp.get(i).toString().equals(solcheck.get(i).toString()))
                flag = false;

       }
        assertTrue(flag);
    }

    @Test
    void  NullCheck(){
        SearchableMaze sm = new SearchableMaze(null);
        Exception exception = assertThrows(Exception.class, () -> bestfs.solve(sm));
        assertEquals(" Null searchable object ", exception.getMessage());


    }
    @Test
    void NoSolution() {
        int[][] m = {{0, 1, 1}, {1, 1, 1}, {1, 1, 0}};
        Maze maze = new Maze(new Position(0, 0),new Position(m.length - 1, m[0].length - 1),m);
        Solution solution = null;
        try {
            solution = bestfs.solve(new SearchableMaze(maze));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(solution.getSolutionPath().size(), 0);
    }

    @Test
    void NoSolution3D() {
        int[][][] m = {{{0, 1, 1}, {1, 1, 1}, {1, 1, 1}}, {{0, 1,1}, {1, 1, 1}, {1, 1, 0}}};
        Maze3D maze = new Maze3D(new Position3D(0, 0,0),new Position3D(m.length - 1, m[0].length - 1 , m[0][0].length-1),m);
        Solution solution = null;
        try {
            solution = bestfs.solve(new SearchableMaze3D(maze));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(solution.getSolutionPath().size(), 0);
    }



}