package local.tin.examples.jetty.embedded.helloWorld.web;

import java.util.logging.Logger;
import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettySSLServer;

/**
 *
 * @author benitodarder
 */
public class HelloWorldSSLServer extends AbstractJettySSLServer {

    private static final Logger LOGGER = Logger.getLogger(HelloWorldSSLServer.class.getCanonicalName());


    public HelloWorldSSLServer(ISSLConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }


}
