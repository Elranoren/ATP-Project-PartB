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

    /**
     * @param b byte array of the maze
     * @throws IOException
     */
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

    /**
     * @param i the start index of the content of the maze ( after the parameters of the maze )
     * @param b byte array of the maze
     * @throws IOException
     */
    public void writeContentOfMaze(int i , byte[] b) throws IOException {
        while(i<b.length) {
            i = oneZeroSequenceCounter(b, i,1);
            i =oneZeroSequenceCounter(b,i,0);
        }
    }


    /**
     * @param b byte array of the maze
     * @param i the next index of array b
     * @param val 0 if we want to count sequence of zero , 1 if we want to count sequence of one
     * @return the current index of array b
     * @throws IOException
     */
    public int oneZeroSequenceCounter(byte[] b, int i,int val) throws IOException {
        boolean flag = true;
        int zOrOcount=0;
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
