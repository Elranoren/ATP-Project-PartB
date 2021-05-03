package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{


    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        Solution sol;
        ISearchingAlgorithm searchingAlgorithm ;

        try {
            ObjectInputStream fromClient= new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient= new ObjectOutputStream(outToClient);
            Maze m = (Maze) fromClient.readObject();
           // m = new Maze(new Position(0,0),new Position(1,1),new int[2][2]);
           // m.getMaze()[0][0]=0;
           // m.getMaze()[0][1]=1;
            //m.getMaze()[1][0]=1;
            //m.getMaze()[1][1]=0;
            int mazeId =(m.toString()).hashCode();
            String tempDirectoryPath =System.getProperty("java.io.tmpdir");
            String mazeIdPath =tempDirectoryPath + mazeId;

            File file = new File (mazeIdPath);
            ISearchable searchable = new SearchableMaze(m);


            if (!file.exists())
            {
                //System.out.println("File not Exist");
                if (Configurations.p.getProperty("mazeSearchingAlgorithm").equals("BFS"))
                    searchingAlgorithm = new BreadthFirstSearch();
                else if (Configurations.p.getProperty("mazeSearchingAlgorithm").equals("DFS"))
                    searchingAlgorithm = new DepthFirstSearch();
                else
                    searchingAlgorithm = new BestFirstSearch();
                sol = searchingAlgorithm.solve(searchable);
                FileOutputStream outFile = new FileOutputStream(mazeIdPath);
                ObjectOutputStream o = new ObjectOutputStream(outFile);
                o.writeObject(sol);
                o.close();
            }
            else {
                //System.out.println("File Is Exist");
                FileInputStream inFile = new FileInputStream(mazeIdPath);
                ObjectInputStream o = new ObjectInputStream(inFile);
                sol= (Solution) o.readObject();
                o.close();
            }

            toClient.writeObject(sol);
            inFromClient.close();
            outToClient.close();
        }
        catch (Exception e) {
        }
    }
}
