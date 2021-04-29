package Server;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
        int p= ProjectProperties.propertyVal;
    }
    public void start(){
        int threadPoolSize;
        try {
            threadPoolSize = Integer.parseInt(ProjectProperties.p.getProperty("threadPoolSize"));
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
                Socket clientS = serverSocket.accept();
                threadPool.submit(()->{handle(clientS);});
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


    public static class ProjectProperties{
        static int propertyVal = getProperty();
        protected static Properties p;
        private static int getProperty(){
            p= new Properties();
            InputStream in=null;
            try {
                in = new FileInputStream("config.properties");
                if (in == null){
                    System.out.println(" Bad input");
                    return -1;
                }
                p.load(in);

            } catch (IOException e) {

            }
        return 1;
        }

    }

}
