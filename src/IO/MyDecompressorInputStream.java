package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(FileInputStream in) {
        this.in = in;

    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}