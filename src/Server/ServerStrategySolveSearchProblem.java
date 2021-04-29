package Server;

import algorithms.mazeGenerators.Maze;
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
            int mazeId =(m.toString()).hashCode();
            String tempDirectoryPath =System.getProperty("java.io.tmpdir");
            String mazeIdPath =tempDirectoryPath + mazeId;

            File file = new File (mazeIdPath);
            ISearchable searchable = new SearchableMaze(m);


            if (!file.exists() && file.isDirectory())
            {
                if (Server.ProjectProperties.p.getProperty("mazeSearchingAlgorithm").equals("BFS"))
                    searchingAlgorithm = new BreadthFirstSearch();
                else if (Server.ProjectProperties.p.getProperty("mazeSearchingAlgorithm").equals("DFS"))
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
