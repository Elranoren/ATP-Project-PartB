package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{


    /**
     * @param inFromClient input from the client
     * @param outToClient output to the client
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            ObjectInputStream fromClient= new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient= new ObjectOutputStream(outToClient);
            Maze m = (Maze) fromClient.readObject();
            int mazeId =(m.toString()).hashCode();
            String tempDirectoryPath =System.getProperty("java.io.tmpdir");
            String mazeIdPath =tempDirectoryPath + mazeId;

            File file = new File (mazeIdPath);
            ISearchable searchable = new SearchableMaze(m);
            Solution sol = cheakIfAlreadySolved(file,searchable,mazeIdPath);
            toClient.writeObject(sol);
            inFromClient.close();
            outToClient.close();
        }
        catch (Exception e) {
        }
    }


    /**
     * @param file new file of the maze solution
     * @param searchable searchable of the maze
     * @param mazeIdPath the path of the maze
     * @return the solution of the maze
     */
    public Solution cheakIfAlreadySolved(File file,ISearchable searchable,String mazeIdPath){
        Solution sol=null;
        ISearchingAlgorithm searchingAlgorithm ;
        if (!file.exists())
        {
            try {
                if (Configurations.p.getProperty("mazeSearchingAlgorithm").equals("BreadthFirstSearch"))
                    searchingAlgorithm = new BreadthFirstSearch();
                else if (Configurations.p.getProperty("mazeSearchingAlgorithm").equals("DepthFirstSearch"))
                    searchingAlgorithm = new DepthFirstSearch();
                else
                    searchingAlgorithm = new BestFirstSearch();
            }
            catch (Exception e)
            {
                searchingAlgorithm = new BestFirstSearch();
            }

            try {
                sol = searchingAlgorithm.solve(searchable);
                FileOutputStream outFile = new FileOutputStream(mazeIdPath);
                ObjectOutputStream o = new ObjectOutputStream(outFile);
                synchronized(this){
                    o.writeObject(sol);
                }
                o.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {

            FileInputStream inFile = null;
            try {
                inFile = new FileInputStream(mazeIdPath);
                ObjectInputStream o = new ObjectInputStream(inFile);
                sol= (Solution) o.readObject();
                o.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sol;
    }
}
