package local.tin.tests.jetty.embedded.core.base.web.interfaces;

import java.util.Map;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public interface IAbstractJettyServer extends Runnable {

    /**
     * Starts and joins servers. Requests are accepted.
     *
     * @throws JettyEmbeddedCommonException
     */
    public void startAndJoinServer() throws JettyEmbeddedCommonException;

    /**
     * Stops server.
     *
     * @throws JettyEmbeddedCommonException
     */
    public void stopServer() throws JettyEmbeddedCommonException;

    /**
     * Destroy servers.
     *
     * @throws JettyEmbeddedCommonException
     */
    public void destroyServer() throws JettyEmbeddedCommonException;

    /**
     * Returns initial parameters.
     *
     * @return Map of String and String
     */
    public Map<String, String> getInitParameters() throws JettyEmbeddedCommonException;

    public Logger getLogger();
}
