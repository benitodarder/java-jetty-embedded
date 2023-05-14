package local.tin.examples.jetty.embedded.web.page.web;

import java.util.logging.Logger;
import local.tin.tests.jetty.embedded.core.base.IWebPageConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyWebServer;

/**
 *
 * @author benitodarder
 */
public class WebPageServer extends AbstractJettyWebServer {


    private static final Logger LOGGER = Logger.getLogger(WebPageServer.class.getCanonicalName());

    public WebPageServer(IWebPageConfiguration configuration) {
        super(configuration);        
        
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

 

}
