package local.tin.tests.base.service.web;

import java.util.Map;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author benitodarder
 */
public abstract class AbstractJettyServer {

    public static final String PATH_SPEC = "/*";
    public static final int INIT_ORDER = 0;
    public static final String SERVER_HTTP_PORT_PARAMETER = "jersey.config.server.http.port";
    public static final String SERVER_PATH_PARAMETER = "jersery.config.server.path";
    public static final String SERVER_PROVIDER_CLASSES = "jersey.config.server.provider.classnames";
    public static final String SERVER_PROVIDER_PACKAGES = "jersey.config.server.provider.packages";
    public static final String SERVER_SHUTOWN_HOOK = "server.shutdown.hook";
    private final Server jettyServer;
    private final ServletHolder jerseyServlet;
    
    public AbstractJettyServer(Map<String, Object> parameters) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath((String) parameters.get(SERVER_PATH_PARAMETER));
        jettyServer = new Server((int) parameters.get(SERVER_HTTP_PORT_PARAMETER));
        jettyServer.setHandler(context);
        jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, PATH_SPEC);
        jerseyServlet.setInitOrder(INIT_ORDER);
        if (parameters.containsKey(SERVER_PROVIDER_CLASSES)) {
            jerseyServlet.setInitParameter(SERVER_PROVIDER_CLASSES, (String) parameters.get(SERVER_PROVIDER_CLASSES));
        }

        if (parameters.containsKey(SERVER_PROVIDER_PACKAGES)) {
            jerseyServlet.setInitParameter(SERVER_PROVIDER_PACKAGES, (String) parameters.get(SERVER_PROVIDER_PACKAGES));
        }

        if (parameters.containsKey(SERVER_SHUTOWN_HOOK)) {
            Runtime.getRuntime().addShutdownHook((Thread) parameters.get(SERVER_SHUTOWN_HOOK));
        }
    }

    public void run() throws Exception {
        jettyServer.start();
        jettyServer.join();
    }

    public void stop() throws Exception {
        jettyServer.stop();
    }

    public void destroy() throws Exception {
        jettyServer.destroy();
    }
    
    public Map<String, String> getInitParameters() {
        return jerseyServlet.getInitParameters();
    }
}
