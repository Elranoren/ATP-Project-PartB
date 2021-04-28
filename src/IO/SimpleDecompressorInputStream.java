package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(FileInputStream in) {
        this.in = in;

    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
