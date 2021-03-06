package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
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
        int paramCounter = 0, i = 0, r;
        while (paramCounter <= 5) {
            r = read();
            while (r == 255 && i < b.length) {
                b[i] = -1;
                i++;
                r = read();
            }
            b[i] = (byte) r;
            i++;
            b[i] = (byte) read();
            i++;
            paramCounter++;

        }
        while (i < b.length) {
            r = read();
            if (r == 0) {
                b[i] = (byte) 0;
            } else {
                for (int j = 0; j < r; j++) {
                    b[i] = (byte) 0;
                    i++;
                }
            }
            if(i == b.length)
                break;
            r = read();
            if (r == 0) {
                b[i] = (byte) 1;
            } else {
                for (int j = 0; j < r; j++) {
                    b[i] = (byte) 1;
                    i++;
                }
            }
        }
        return -1;
    }

}
