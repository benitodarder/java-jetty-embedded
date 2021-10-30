package local.tin.examples.jetty.embedded.logging.web;

import local.tin.examples.jetty.embedded.logging.web.filters.LoggingFilter;
import java.util.EnumSet;
import java.util.Map;
import javax.servlet.DispatcherType;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;
import local.tin.tests.jetty.embedded.core.base.web.interfaces.IAbstractJettyServer;
import org.eclipse.jetty.server.CustomRequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Slf4jRequestLogWriter;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author benitodarder
 */
public class JettyLoggingServer implements IAbstractJettyServer {

    public static final int INIT_ORDER = 0;
    public static final String SERVER_HTTP_PORT_PARAMETER = "jersey.config.server.http.port";
    public static final String SERVER_PATH_PARAMETER = "jersery.config.server.path";
    public static final String SERVER_PROVIDER_CLASSES = "jersey.config.server.provider.classnames";
    public static final String SERVER_PROVIDER_PACKAGES = "jersey.config.server.provider.packages";
    public static final String SERVER_SHUTOWN_HOOK = "server.shutdown.hook";
    private final Server jettyServer;
    private final ServletHolder jerseyServlet;
    private static final Logger LOGGER = Logger.getLogger(JettyLoggingServer.class);

    public JettyLoggingServer(IConfiguration parameters) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(parameters.getApplicationPath());
        context.addFilter(LoggingFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, parameters.getURLPattern());
        jerseyServlet.setInitOrder(INIT_ORDER);
        jerseyServlet.setInitParameter(SERVER_PROVIDER_CLASSES, parameters.getControllers());

        Slf4jRequestLogWriter slfjRequestLogWriter = new Slf4jRequestLogWriter();
        slfjRequestLogWriter.setLoggerName("appenderLogger");
        String format = "%{client}a - %u %{yyyy-MM-dd'T'HH:mm:ss.SSSZ}t %m %{ms}Tms \"%U\" %s %O \"%{Referer}i\" \"%{User-Agent}i\"";
        CustomRequestLog customRequestLog = new CustomRequestLog(slfjRequestLogWriter, format);
        
        ContextHandlerCollection handlers = new ContextHandlerCollection(context);

        Runtime.getRuntime().addShutdownHook(parameters.getShutdownHook());
        jettyServer = new Server(parameters.getHttpPort());
        jettyServer.setRequestLog(customRequestLog);
        jettyServer.setHandler(handlers);
    }

    public Logger getLogger() {
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
