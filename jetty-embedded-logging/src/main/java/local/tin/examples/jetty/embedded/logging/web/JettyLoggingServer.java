package local.tin.examples.jetty.embedded.logging.web;

import local.tin.examples.jetty.embedded.logging.Configuration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyLoggingServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author benitodarder
 */
public class JettyLoggingServer extends AbstractJettyLoggingServer {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JettyLoggingServer.class.getCanonicalName());

    public JettyLoggingServer(Configuration parameters) {
        
        super(parameters);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
