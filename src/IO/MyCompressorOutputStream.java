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
        while(i<b.length) {
            i = oneZeroSequenceCounter(b, i,0,1);
            i =oneZeroSequenceCounter(b,i,0,0);
        }
    }

    public int oneZeroSequenceCounter(byte[] b, int i, int zOrOcount,int val) throws IOException {
        boolean flag = true;
        while (flag) {
            if (i == b.length || b[i] == val) {
                flag = false;
                if (zOrOcount <= 255) {
                    write(zOrOcount);
                } else {
                    while (zOrOcount > 255) {
                        zOrOcount -= 255;
                        write(-1);
                        write(0);
                    }
                    write(zOrOcount);
                }

            }
            else {
                zOrOcount++;
                i++;
            }
        }
        return i;
    }

}
