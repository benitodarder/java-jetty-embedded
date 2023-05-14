package local.tin.examples.jetty.embedded.logging.web;

import local.tin.examples.jetty.embedded.logging.Configuration;
import local.tin.tests.java.embedded.core.slf4j.web.AbstractJettyLoggingServer;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class JettyLoggingServer extends AbstractJettyLoggingServer {

    private static final Logger LOGGER = Logger.getLogger(JettyLoggingServer.class);

    public JettyLoggingServer(Configuration parameters) {
        
        super(parameters);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
