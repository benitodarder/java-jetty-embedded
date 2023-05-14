package local.tin.tests.jetty.embedded.crud.service.web;

import java.util.logging.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyNoSSLServer;

/**
 *
 * @author benitodarder
 */
public class ProductServer extends AbstractJettyNoSSLServer {
    
    private static final Logger LOGGER = Logger.getLogger(ProductServer.class.getCanonicalName());

    public ProductServer(IConfiguration parameters) {
        super(parameters);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
