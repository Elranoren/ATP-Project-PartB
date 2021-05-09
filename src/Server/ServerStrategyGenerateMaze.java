package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    /**
     * @param inFromClient the input stream from the client
     * @param outToClient the output stream to the client
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient)  {
        try {
            IMazeGenerator mazeGeneratingAlgorithm;
            ObjectInputStream fromClient= new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient= new ObjectOutputStream(outToClient);
            int[] mazeDimens = (int[]) fromClient.readObject();
            try {
                if (Configurations.p.getProperty("mazeGeneratingAlgorithm").equals("SimpleMazeGenerator"))
                    mazeGeneratingAlgorithm = new SimpleMazeGenerator();
                else if (Configurations.p.getProperty("mazeGeneratingAlgorithm").equals("MyMazeGenerator"))
                    mazeGeneratingAlgorithm = new MyMazeGenerator();
                else
                    mazeGeneratingAlgorithm = new EmptyMazeGenerator();
            }
            catch (Exception e)
            {
                mazeGeneratingAlgorithm = new MyMazeGenerator();
            }
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
