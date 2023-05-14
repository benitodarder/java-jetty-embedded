package local.tin.examples.jetty.embedded.camelCaser.web.filters;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;


/**
 *
 * @author benitodarder
 */
public class SimpleServletInputStream extends ServletInputStream {

    private static final Logger LOGGER = Logger.getLogger(SimpleServletInputStream.class.getCanonicalName());
    private final InputStream is;

    public SimpleServletInputStream(byte[] content) {
        this.is = new ByteArrayInputStream(content);
    }

    @Override
    public boolean isFinished() {
        try {
            return is.available() == 0;
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Unexpected IOException on isFinished, returning true.", ex);
            return true;
        }
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        // not used
    }

    @Override
    public int read() throws IOException {
        return this.is.read();
    }

    @Override
    public void close() throws IOException {
        super.close();
        is.close();
    }
}