package local.tin.examples.jetty.embedded.helloWorld.web;

import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyNoSSLServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author benitodarder
 */
public class HelloWorldServer extends AbstractJettyNoSSLServer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldServer.class.getCanonicalName());
    
    public HelloWorldServer(IConfiguration parameters) {
        super(parameters);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
