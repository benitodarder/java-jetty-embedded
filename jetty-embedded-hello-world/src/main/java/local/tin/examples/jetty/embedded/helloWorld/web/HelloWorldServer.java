package local.tin.examples.jetty.embedded.helloWorld.web;

import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyNoSSLServer;

/**
 *
 * @author benitodarder
 */
public class HelloWorldServer extends AbstractJettyNoSSLServer {
    
    private static final Logger LOGGER = Logger.getLogger(HelloWorldServer.class);
    
    public HelloWorldServer(IConfiguration parameters) {
        super(parameters);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
