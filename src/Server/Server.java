package Server;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.stop = false;
        int p= Configurations.propertyVal;
    }
    public void start(){
        int threadPoolSize;
        try {
            String z = Configurations.p.getProperty("threadPoolSize");
            threadPoolSize = Integer.parseInt(z);
        }
        catch (Exception e)
        {
            threadPoolSize=5;
        }
        threadPool = Executors.newFixedThreadPool(threadPoolSize);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop){
                try {
                    Socket clientS = serverSocket.accept();
                    threadPool.submit(() -> {
                        handle(clientS);
                    });
                } catch (SocketTimeoutException e) {
                    System.out.println("Waiting for a Client");
                }
            }
            serverSocket.close();
            threadPool.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void handle(Socket clientS) {
        try {
            strategy.applyStrategy(clientS.getInputStream(),clientS.getOutputStream());
            clientS.close();
        } catch (IOException e) {
        }

    }

    public void stop(){
        stop=true;
    }

}
