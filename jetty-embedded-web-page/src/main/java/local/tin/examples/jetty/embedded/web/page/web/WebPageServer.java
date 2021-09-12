package local.tin.examples.jetty.embedded.web.page.web;

import java.io.File;
import java.util.Map;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;
import local.tin.tests.jetty.embedded.core.base.web.interfaces.IAbstractJettyServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author benitodarder
 */
public class WebPageServer implements IAbstractJettyServer {

    public static final int INIT_ORDER = 0;
    public static final String SERVER_HTTP_PORT_PARAMETER = "jersey.config.server.http.port";
    public static final String SERVER_PATH_PARAMETER = "jersery.config.server.path";
    public static final String SERVER_PROVIDER_CLASSES = "jersey.config.server.provider.classnames";
    public static final String SERVER_PROVIDER_PACKAGES = "jersey.config.server.provider.packages";
    public static final String SERVER_SHUTOWN_HOOK = "server.shutdown.hook";
    private final Server jettyServer;
    private final ServletHolder jerseyServlet;
    private static final Logger LOGGER = Logger.getLogger(WebPageServer.class);

    public WebPageServer(IConfiguration parameters) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(parameters.getApplicationPath());
        jettyServer = new Server(parameters.getHttpPort());
        jettyServer.setHandler(context);
        jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, parameters.getURLPattern());
        jerseyServlet.setInitOrder(INIT_ORDER);
        jerseyServlet.setInitParameter(SERVER_PROVIDER_CLASSES, parameters.getControllers());
        //for static  content:
        ResourceHandler resH = new ResourceHandler();
        resH.setDirectoriesListed(true);
        resH.setWelcomeFiles(new String[]{ "index.html" });
        resH.setResourceBase(getResourceBase());
        ContextHandler resCtx = new ContextHandler();
        resCtx.setHandler(resH);

        //Add both ContextHandlers to server:
        ContextHandlerCollection handlers = new ContextHandlerCollection(resCtx, context);
        jettyServer.setHandler(handlers);
        Runtime.getRuntime().addShutdownHook(parameters.getShutdownHook());
    }

    private static String getResourceBase() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".")
                .append(File.separator)
                .append("src")
                .append(File.separator)
                .append("main")
                .append(File.separator)
                .append("resources")
                .append(File.separator)
                .append("public");
        return stringBuilder.toString();
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
