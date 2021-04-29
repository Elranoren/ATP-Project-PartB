package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient)  {

        try {
            ObjectInputStream fromClient= new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient= new ObjectOutputStream(outToClient);
            int[] mazeDimens = (int[]) fromClient.readObject();
            Maze m = new MyMazeGenerator().generate(mazeDimens[0],mazeDimens[1]);
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
