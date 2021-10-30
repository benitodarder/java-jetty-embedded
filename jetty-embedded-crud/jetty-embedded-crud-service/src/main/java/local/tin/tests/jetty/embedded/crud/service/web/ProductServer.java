package local.tin.tests.jetty.embedded.crud.service.web;

import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyNoSSLServer;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class ProductServer extends AbstractJettyNoSSLServer {
    
    private static final Logger LOGGER = Logger.getLogger(ProductServer.class);

    public ProductServer(IConfiguration parameters) {
        super(parameters);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
