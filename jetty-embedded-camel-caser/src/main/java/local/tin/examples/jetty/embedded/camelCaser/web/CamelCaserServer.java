package local.tin.examples.jetty.embedded.camelCaser.web;

import local.tin.tests.java.embedded.core.slf4j.web.AbstractJettyGralLoggingServer;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;

/**
 *
 * @author benitodarder
 */
public class CamelCaserServer extends AbstractJettyGralLoggingServer {

    private static final Logger LOGGER = Logger.getLogger(CamelCaserServer.class);


    public CamelCaserServer(ISSLConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }


}
