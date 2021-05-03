package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
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
        String byteB ="";
        while(i<b.length) {
            for (int j = 0; j < 8 ; j++) {
                byteB+= b[i] ;
                i++;
                if (i == b.length)
                    break;
            }

            try {
                int b1=Integer.parseInt(byteB,2);
                write((byte)b1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byteB ="";
        }
    }



}
