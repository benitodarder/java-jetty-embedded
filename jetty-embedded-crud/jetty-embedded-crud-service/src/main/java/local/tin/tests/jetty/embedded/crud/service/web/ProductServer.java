package local.tin.tests.jetty.embedded.crud.service.web;

import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyNoSSLServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author benitodarder
 */
public class ProductServer extends AbstractJettyNoSSLServer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServer.class.getCanonicalName());

    public ProductServer(IConfiguration parameters) {
        super(parameters);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
