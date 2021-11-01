package local.tin.examples.jetty.embedded.web.page.web;

import local.tin.tests.jetty.embedded.core.base.IWebPageConfiguration;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.web.AbstractWebJettyServer;

/**
 *
 * @author benitodarder
 */
public class WebPageServer extends AbstractWebJettyServer {


    private static final Logger LOGGER = Logger.getLogger(WebPageServer.class);

    public WebPageServer(IWebPageConfiguration configuration) {
        super(configuration);        
        
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

 

}
