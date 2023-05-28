package local.tin.examples.jetty.embedded.helloWorld.web;


import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettySSLServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author benitodarder
 */
public class HelloWorldSSLServer extends AbstractJettySSLServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldSSLServer.class.getCanonicalName());


    public HelloWorldSSLServer(ISSLConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }


}
