package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;

    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    public int read(byte[] b) throws IOException {
        byte[] paramArray = new byte[30];
        byte[] mazeArray = new byte[1000000];
        int mazeIndex = 0;
        int paramCounter = 0, i = 0, r, sum;
        while (paramCounter <= 5) {
            r = read();
            while (r == 255 && i < b.length) {
                paramArray[i] = -1;
                i++;
                r = read();
            }
            paramArray[i] = (byte) r;
            i++;
            paramArray[i] = (byte) read();
            paramCounter++;

        }
        i++;
        paramArray = Arrays.copyOfRange(paramArray, 0, i);
        while (i < b.length) {
            r = read();
            sum = r;
            if (sum == 0) {
                mazeArray[mazeIndex] = (byte) 0;
                mazeIndex++;
            } else {
                for (int j = 0; j < sum; j++) {
                    mazeArray[mazeIndex] = (byte) 0;
                    mazeIndex++;
                }
            }
            i++;
            r = read();
            sum = r;
            if (sum == 0) {
                mazeArray[mazeIndex] = (byte) 1;
                mazeIndex++;
            } else {
                for (int j = 0; j < sum; j++) {
                    mazeArray[mazeIndex] = (byte) 1;
                    mazeIndex++;
                }
            }
            i++;

        }
        mazeArray = Arrays.copyOfRange(mazeArray, 0, mazeIndex + 1);
        b = new byte[mazeArray.length + paramArray.length];
        b = Arrays.copyOfRange(paramArray, 0, paramArray.length);
        b = Arrays.copyOfRange(mazeArray, paramArray.length + 1, mazeArray.length);
        return -1;
    }

}
