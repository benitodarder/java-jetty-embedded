package local.tin.examples.jetty.embedded.camelCaser.web;

import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyGralServer;

/**
 *
 * @author benitodarder
 */
public class CammelCaserServer extends AbstractJettyGralServer {

    private static final Logger LOGGER = Logger.getLogger(CammelCaserServer.class);


    public CammelCaserServer(ISSLConfiguration configuration) {
        super(configuration);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }


}
