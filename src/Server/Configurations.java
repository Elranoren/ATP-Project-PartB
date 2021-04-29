package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {
    static int propertyVal = getProperty();
    protected static Properties p;
    private static int getProperty(){
        p= new Properties();
        InputStream in=null;
        try {
            in = new FileInputStream("resources/config.properties");
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
