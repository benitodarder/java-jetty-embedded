package local.tin.examples.jetty.embedded.web.page.web;

import java.util.Map;
import local.tin.examples.jetty.embedded.web.page.Configuration;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyServer;
import local.tin.tests.jetty.embedded.core.base.web.interfaces.IAbstractJettyServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author benitodarder
 */
public class WebPageServer implements IAbstractJettyServer {

    private final Server jettyServer;
    private final ServletHolder jerseyServlet;
    private static final Logger LOGGER = Logger.getLogger(WebPageServer.class);

    public WebPageServer(IConfiguration parameters) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(parameters.getApplicationPath());
        jettyServer = new Server(parameters.getHttpPort());
        jettyServer.setHandler(context);
        jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, parameters.getURLPattern());
        jerseyServlet.setInitOrder(AbstractJettyServer.INIT_ORDER);
        jerseyServlet.setInitParameter(AbstractJettyServer.SERVER_PROVIDER_CLASSES, parameters.getControllers());
        ResourceHandler resH = new ResourceHandler();
        resH.setDirectoriesListed(true);
        resH.setWelcomeFiles(new String[]{ Configuration.DOCUMENT_BASE });
        resH.setResourceBase(Configuration.RESOURCE_BASE);
        ContextHandler resCtx = new ContextHandler();
        resCtx.setHandler(resH);

        ContextHandlerCollection handlers = new ContextHandlerCollection(resCtx, context);
        jettyServer.setHandler(handlers);
        Runtime.getRuntime().addShutdownHook(parameters.getShutdownHook());
    }

    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void run() {
        try {
            startAndJoinServer();
        } catch (JettyEmbeddedCommonException ex) {
            getLogger().error(ex.getMessage());
            getLogger().debug(ex);
        }
    }

    @Override
    public void startAndJoinServer() throws JettyEmbeddedCommonException {
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception ex) {
            throw new JettyEmbeddedCommonException(ex);
        }
    }

    @Override
    public void stopServer() throws JettyEmbeddedCommonException {
        try {
            jettyServer.stop();
        } catch (Exception ex) {
            throw new JettyEmbeddedCommonException(ex);
        }
    }

    @Override
    public void destroyServer() throws JettyEmbeddedCommonException {
        jettyServer.destroy();
    }

    @Override
    public Map<String, String> getInitParameters() {
        return jerseyServlet.getInitParameters();
    }

}
