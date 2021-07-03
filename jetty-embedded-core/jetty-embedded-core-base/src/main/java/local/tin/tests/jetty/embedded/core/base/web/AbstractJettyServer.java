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
public abstract class AbstractJettyServer implements IAbstractJettyServer {

    public static final int INIT_ORDER = 0;
    public static final String SERVER_HTTP_PORT_PARAMETER = "jersey.config.server.http.port";
    public static final String SERVER_PATH_PARAMETER = "jersery.config.server.path";
    public static final String SERVER_PROVIDER_CLASSES = "jersey.config.server.provider.classnames";
    public static final String SERVER_PROVIDER_PACKAGES = "jersey.config.server.provider.packages";
    public static final String SERVER_SHUTOWN_HOOK = "server.shutdown.hook";
    private final Server jettyServer;
    private final ServletHolder jerseyServlet;

    protected abstract Logger getLogger();

    public AbstractJettyServer(IConfiguration configuration) {
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
