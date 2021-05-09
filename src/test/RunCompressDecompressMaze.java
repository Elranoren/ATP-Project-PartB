package test;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {

        int c = 50;
        for (int i = 0; i < 10; i++) {


            String mazeFileName = "savedMaze.maze";
            AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = null; //Generate new maze
            try {
                maze = mazeGenerator.generate(c, c);
            } catch (Exception e) {
                e.printStackTrace();
            }
            c+=50;
            try {
// save maze to a file
                OutputStream out = new MyCompressorOutputStream(new
                        FileOutputStream(mazeFileName));
                //maze.print();
                //System.out.println(maze.getStartPosition());
                //System.out.println(maze.getGoalPosition());
                byte[] h = maze.toByteArray();
                out.write(h);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte savedMazeBytes[] = new byte[0];
            try {
//read maze from file
                InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
                savedMazeBytes = new byte[maze.toByteArray().length];
                in.read(savedMazeBytes);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Maze loadedMaze = new Maze(savedMazeBytes);
            boolean areMazesEquals =
                    Arrays.equals(loadedMaze.toByteArray(), maze.toByteArray());
            System.out.println(String.format("Mazes equal: %s", areMazesEquals));
//maze should be equal to loadedMaze

        }
    }
}