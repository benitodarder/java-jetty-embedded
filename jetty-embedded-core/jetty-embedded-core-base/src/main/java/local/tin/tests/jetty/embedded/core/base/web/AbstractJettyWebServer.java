package local.tin.tests.jetty.embedded.core.base.web;

import local.tin.tests.jetty.embedded.core.base.IWebPageConfiguration;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 *
 * @author benitodarder
 */
public abstract class AbstractJettyWebServer extends AbstractJettyServer {

    public AbstractJettyWebServer(IWebPageConfiguration configuration) {
        super(configuration);
        
        ResourceHandler resH = new ResourceHandler();
        resH.setDirectoriesListed(true);
        resH.setWelcomeFiles(new String[]{configuration.getDocumentBase()});
        resH.setResourceBase(configuration.getResourceBase());
        
        ContextHandler resCtx = new ContextHandler();
        resCtx.setHandler(resH);

        ContextHandlerCollection handlers = new ContextHandlerCollection(resCtx, getServletContextHandler());
        getJettyServer().setHandler(handlers);
        getLogger().debug("AbstractJettyWebServer ResourceHandler.getDocumentBase : {0}", resH.getResourceBase());
        StringBuilder stringBuilder = new StringBuilder();
        for (String current : resH.getWelcomeFiles()) {
            stringBuilder.append(" ").append(current).append(" ");
        }
        getLogger().debug("AbstractJettyWebServer ResourceHandler.getWelcomeFiles: {0}", stringBuilder.toString());
    }

}
