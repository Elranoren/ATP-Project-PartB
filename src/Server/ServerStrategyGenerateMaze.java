package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient)  {
        try {
            IMazeGenerator mazeGeneratingAlgorithm=null ;
            ObjectInputStream fromClient= new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient= new ObjectOutputStream(outToClient);
            int[] mazeDimens = (int[]) fromClient.readObject();
            if (Configurations.p.getProperty("mazeGeneratingAlgorithm").equals("MyMazeGenerator"))
                    mazeGeneratingAlgorithm = new MyMazeGenerator();
            Maze m = mazeGeneratingAlgorithm.generate(mazeDimens[0],mazeDimens[1]);
            ByteArrayOutputStream bArr = new ByteArrayOutputStream();
            OutputStream out = new MyCompressorOutputStream(bArr);
            out.write(m.toByteArray());
            out.flush();
            toClient.writeObject(bArr.toByteArray());
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
        }

    }
}
