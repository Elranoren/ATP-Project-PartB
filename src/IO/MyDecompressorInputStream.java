package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;

    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    /**
     * @param b byte array to update according to the input of the maze
     * @return -1
     * @throws IOException
     */
    public int read(byte[] b) throws IOException {
        int paramCounter = 0, i = 0, r,row=0,col=0;
        while (paramCounter <= 5) {
            r = read();
            while (r == 255 && i < b.length) {
                if (paramCounter==4)
                    row+=r;
                if (paramCounter==5)
                    col+=r;
                b[i] = -1;
                i++;
                r = read();
            }
            if (paramCounter==4 )
                row+=r;
            if (paramCounter==5 )
                col+=r;
            b[i] = (byte) r;
            i++;
            b[i] = (byte) read();
            i++;

            paramCounter++;

        }
        int restBit = (row*col)%8;
        String byteToBinary="";
        while (i < b.length -restBit) {
            r = read();

            byteToBinary =String.format("%8s", Integer.toBinaryString((byte) r & 0xFF)).replace(' ', '0');
            for (int j = 0; j < byteToBinary.length(); j++) {
                String s = byteToBinary.substring(j,j+1);
                char c = s.charAt(0);
                b[i]=(byte)Character.getNumericValue(c);
                i++;
                if(i == b.length -restBit)
                    break;

            }
        }
        if (restBit!=0)
        {

            r = read();
            byteToBinary =String.format("%8s", Integer.toBinaryString((byte) r & 0xFF)).replace(' ', '0');
            for (int j = 8-restBit; j < 8; j++) {
                String s = byteToBinary.substring(j,j+1);
                char c = s.charAt(0);
                b[i]=(byte)Character.getNumericValue(c);
                i++;
                if (i==b.length)
                    break;

            }
        }
        return -1;
    }
}
