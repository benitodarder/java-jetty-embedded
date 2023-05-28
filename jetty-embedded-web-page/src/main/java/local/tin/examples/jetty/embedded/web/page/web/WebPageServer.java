package local.tin.examples.jetty.embedded.web.page.web;

import local.tin.tests.jetty.embedded.core.base.IWebPageConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyWebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author benitodarder
 */
public class WebPageServer extends AbstractJettyWebServer {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebPageServer.class.getCanonicalName());

    public WebPageServer(IWebPageConfiguration configuration) {
        super(configuration);        
        
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

 

}
