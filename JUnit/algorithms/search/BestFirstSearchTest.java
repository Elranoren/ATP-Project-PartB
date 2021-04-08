package algorithms.search;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch bestfs = new BestFirstSearch();
    @Test
    void  TimeCheckTest(){
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze sm = new SearchableMaze(maze);
        assertTrue(bestfs.measureAlgorithmTimeMillisOnSearchingAlgorithm(sm)<=60000);

    }
    @Test
    void  TimeCheckTest3D(){
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(100, 100,100);
        SearchableMaze3D sm = new SearchableMaze3D(maze);
        assertTrue(bestfs.measureAlgorithmTimeMillisOnSearchingAlgorithm(sm)<=60000);

    }
    @Test
    void  WrongParametersCheck(){
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(-19, -96);
        SearchableMaze sm = new SearchableMaze(maze);
        Solution s = bestfs.solve(sm);
        ArrayList<AState> sp = s.getSolutionPath();
        assertTrue(sp.size()==3 || sp.size()==2 );

    }

    @Test
    void  WrongParametersCheck3D(){
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(-40,-19, -96);
        SearchableMaze3D sm = new SearchableMaze3D(maze);
        Solution s = bestfs.solve(sm);
        ArrayList<AState> sp = s.getSolutionPath();
        assertTrue(sp.size()>=2 && sp.size()<=4);

    }

    @Test
    void  PathCheck(){
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
        Solution s = bestfs.solve(sm);
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
        Solution s = bestfs.solve(sm);
        ArrayList<AState> sp = s.getSolutionPath();
        assertTrue(sp.size()==0);

    }


}