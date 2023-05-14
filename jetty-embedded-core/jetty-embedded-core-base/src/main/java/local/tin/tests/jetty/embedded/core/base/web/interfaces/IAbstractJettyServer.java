package local.tin.tests.jetty.embedded.core.base.web.interfaces;

import java.util.Map;
import java.util.logging.Logger;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;

/**
 *
 * @author benitodarder
 */
public interface IAbstractJettyServer<T> extends Runnable {

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
