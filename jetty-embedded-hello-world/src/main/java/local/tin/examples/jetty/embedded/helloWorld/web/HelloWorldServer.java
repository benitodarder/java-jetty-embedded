package local.tin.examples.jetty.embedded.helloWorld.web;

import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyServer;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;

/**
 *
 * @author benitodarder
 */
public class HelloWorldServer extends AbstractJettyServer {
    
    private static final Logger LOGGER = Logger.getLogger(HelloWorldServer.class);
    
    public HelloWorldServer(IConfiguration parameters) {
        super(parameters);
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

}
