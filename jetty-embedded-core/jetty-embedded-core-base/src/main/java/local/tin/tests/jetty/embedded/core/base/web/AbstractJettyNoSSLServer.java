package local.tin.tests.jetty.embedded.core.base.web;

import java.util.Map;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;
import local.tin.tests.jetty.embedded.core.base.web.interfaces.IAbstractJettyServer;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;

/**
 *
 * @author benitodarder
 */
public abstract class AbstractJettyNoSSLServer extends AbstractJettyServer {

    private final Server jettyServer;
    private final ServletHolder jerseyServlet;

    public AbstractJettyNoSSLServer(IConfiguration configuration) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(configuration.getApplicationPath());
        jettyServer = new Server(configuration.getHttpPort());
        jettyServer.setHandler(context);
        jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, configuration.getURLPattern());
        jerseyServlet.setInitOrder(INIT_ORDER);
        jerseyServlet.setInitParameter(SERVER_PROVIDER_CLASSES, configuration.getControllers());
        Runtime.getRuntime().addShutdownHook(configuration.getShutdownHook());

    }

    @Override
    protected Server getServer() {
        return jettyServer;
    }

    @Override
    protected ServletHolder getServleHolder() {
        return jerseyServlet;
    }

 
}
