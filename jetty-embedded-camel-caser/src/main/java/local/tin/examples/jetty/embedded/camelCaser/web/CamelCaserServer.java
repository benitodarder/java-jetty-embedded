package local.tin.examples.jetty.embedded.camelCaser.web;

import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyGralServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author benitodarder
 */
public class CamelCaserServer extends AbstractJettyGralServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamelCaserServer.class);


    public CamelCaserServer(ISSLConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }


}
