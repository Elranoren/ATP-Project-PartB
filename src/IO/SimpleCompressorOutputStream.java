package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    public void write(byte[] b) throws IOException {
        int paramCounter=0,i=0;
        while(paramCounter<=5){
           while(b[i]==-1){
               i++;
               write(-1);
           }
           write(b[i]);
           write(0);
           i+=2;
           paramCounter++;
        }
        writeContentOfMaze(i,b);

    }
    public void writeContentOfMaze(int i , byte[] b) throws IOException {
        boolean flag = true;
        int zCount=0,oCount=0;
        while(i<b.length) {
            zCount = 0;
            oCount = 0;

            while (flag == true) {
                if (i == b.length || b[i] == 1) {
                    flag = false;
                    if (zCount <= 255) {
                        write(zCount);
                        zCount = 0;
                    } else {
                        while (zCount > 255) {
                            zCount -= 255;
                            write(-1);
                            write(0);
                        }
                        write(zCount);
                        zCount = 0;
                    }

                } else {
                    zCount++;
                    i++;
                }
            }
            while (flag == false) {
                if (i == b.length || b[i] == 0) {
                    flag = true;
                    if (oCount <= 255) {
                        write(oCount);
                        oCount = 0;
                    } else {
                        while (oCount > 255) {
                            oCount -= 255;
                            write(-1);
                            write(0);
                        }
                        write(oCount);
                        oCount = 0;
                    }

                } else {
                    oCount++;
                    i++;
                }
            }

        }
    }


}
