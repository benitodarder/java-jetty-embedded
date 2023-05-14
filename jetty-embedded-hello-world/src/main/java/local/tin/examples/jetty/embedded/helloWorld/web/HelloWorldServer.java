package local.tin.examples.jetty.embedded.helloWorld.web;

import java.util.logging.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyNoSSLServer;

/**
 *
 * @author benitodarder
 */
public class HelloWorldServer extends AbstractJettyNoSSLServer {
    
    private static final Logger LOGGER = Logger.getLogger(HelloWorldServer.class.getCanonicalName());
    
    public HelloWorldServer(IConfiguration parameters) {
        super(parameters);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
