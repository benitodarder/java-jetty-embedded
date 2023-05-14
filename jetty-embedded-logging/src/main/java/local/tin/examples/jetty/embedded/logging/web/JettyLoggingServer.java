package local.tin.examples.jetty.embedded.logging.web;

import java.util.logging.Logger;
import local.tin.examples.jetty.embedded.logging.Configuration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyLoggingServer;

/**
 *
 * @author benitodarder
 */
public class JettyLoggingServer extends AbstractJettyLoggingServer {

    private static final Logger LOGGER = Logger.getLogger(JettyLoggingServer.class.getCanonicalName());

    public JettyLoggingServer(Configuration parameters) {
        
        super(parameters);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
