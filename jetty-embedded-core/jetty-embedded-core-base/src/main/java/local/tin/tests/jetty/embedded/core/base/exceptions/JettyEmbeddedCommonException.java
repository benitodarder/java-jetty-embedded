package local.tin.tests.jetty.embedded.core.base.exceptions;

/**
 *
 * @author benitodarder
 */
public class JettyEmbeddedCommonException extends Exception {

    public JettyEmbeddedCommonException() {
    }

    public JettyEmbeddedCommonException(String string) {
        super(string);
    }

    public JettyEmbeddedCommonException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public JettyEmbeddedCommonException(Throwable thrwbl) {
        super(thrwbl);
    }

    public JettyEmbeddedCommonException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }

}
