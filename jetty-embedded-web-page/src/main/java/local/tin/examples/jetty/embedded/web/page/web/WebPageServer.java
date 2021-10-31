package local.tin.examples.jetty.embedded.web.page.web;

import local.tin.examples.jetty.embedded.web.page.Configuration;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyServer;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 *
 * @author benitodarder
 */
public class WebPageServer extends AbstractJettyServer {


    private static final Logger LOGGER = Logger.getLogger(WebPageServer.class);

    public WebPageServer(IConfiguration configuration) {
        super(configuration);

        ResourceHandler resH = new ResourceHandler();
        resH.setDirectoriesListed(true);
        resH.setWelcomeFiles(new String[]{Configuration.DOCUMENT_BASE});
        resH.setResourceBase(Configuration.RESOURCE_BASE);
        ContextHandler resCtx = new ContextHandler();
        resCtx.setHandler(resH);

        ContextHandlerCollection handlers = new ContextHandlerCollection(resCtx, getServletContextHandler());
        getJettyServer().setHandler(handlers);

    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

 

}
