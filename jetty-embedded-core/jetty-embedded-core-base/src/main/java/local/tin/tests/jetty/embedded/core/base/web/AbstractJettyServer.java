package local.tin.tests.jetty.embedded.core.base.web;

import java.util.Map;
import java.util.logging.Level;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;
import local.tin.tests.jetty.embedded.core.base.web.interfaces.IAbstractJettyServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

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
    private final ServletContextHandler servletContextHandler;
    

    public AbstractJettyServer(IConfiguration configuration) {
        
        servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath(configuration.getApplicationPath());
        jettyServer = new Server(configuration.getHttpPort());
        jettyServer.setHandler(servletContextHandler);
        jerseyServlet = servletContextHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, configuration.getURLPattern());
        jerseyServlet.setInitOrder(INIT_ORDER);
        jerseyServlet.setInitParameter(SERVER_PROVIDER_CLASSES, configuration.getControllers());
        Runtime.getRuntime().addShutdownHook(configuration.getShutdownHook());
    }
    
    
    @Override
    public void run() {
        try {
            startAndJoinServer();
        } catch (JettyEmbeddedCommonException ex) {
            getLogger().log(Level.SEVERE,ex.getMessage());
            getLogger().log(Level.FINER, ex.getMessage(), ex);
        }
    }

    @Override
    public void startAndJoinServer() throws JettyEmbeddedCommonException {
        try {
            getJettyServer().start();
            getJettyServer().join(); 
        } catch (Exception ex) {
            throw new JettyEmbeddedCommonException(ex);
        }
    }

    @Override
    public void stopServer() throws JettyEmbeddedCommonException {
        try {
            getJettyServer().stop();
        } catch (Exception ex) {
            throw new JettyEmbeddedCommonException(ex);
        }
    }

    @Override
    public void destroyServer() throws JettyEmbeddedCommonException {
        getJettyServer().destroy();
    }

    @Override
    public Map<String, String> getInitParameters() {
        return getJerseyServlet().getInitParameters();
    }    

    public final Server getJettyServer() {
        return jettyServer;
    }

    public final ServletHolder getJerseyServlet() {
        return jerseyServlet;
    }

    public final ServletContextHandler getServletContextHandler() {
        return servletContextHandler;
    }
    
    
}
