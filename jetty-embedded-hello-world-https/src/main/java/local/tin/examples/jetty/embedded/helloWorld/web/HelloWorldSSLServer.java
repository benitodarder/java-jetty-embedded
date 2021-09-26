package local.tin.examples.jetty.embedded.helloWorld.web;

import java.util.Map;
import local.tin.examples.jetty.embedded.helloWorld.Configuration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyServer;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;
import static local.tin.tests.jetty.embedded.core.base.web.AbstractJettyServer.INIT_ORDER;
import static local.tin.tests.jetty.embedded.core.base.web.AbstractJettyServer.SERVER_PROVIDER_CLASSES;
import local.tin.tests.jetty.embedded.core.base.web.interfaces.IAbstractJettyServer;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 *
 * @author benitodarder
 */
public class HelloWorldSSLServer implements IAbstractJettyServer {

    private static final Logger LOGGER = Logger.getLogger(HelloWorldSSLServer.class);
    private final Server jettyServer;
    private final ServletHolder jerseyServlet;

    public HelloWorldSSLServer(IConfiguration parameters) {
        Configuration configuration = (Configuration) parameters;

        jettyServer = new Server();

        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(configuration.getJKSPath());
        sslContextFactory.setKeyStorePassword(configuration.getKeystorePassword());
        sslContextFactory.setKeyManagerPassword(configuration.getJKSPassword());

        ServerConnector sslConnector = new ServerConnector(jettyServer,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https));
        sslConnector.setPort(8443);

        jettyServer.setConnectors(new Connector[]{sslConnector});

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(configuration.getApplicationPath());

        jettyServer.setHandler(context);
        jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, configuration.getURLPattern());
        jerseyServlet.setInitOrder(INIT_ORDER);
        jerseyServlet.setInitParameter(SERVER_PROVIDER_CLASSES, configuration.getControllers());
        Runtime.getRuntime().addShutdownHook(configuration.getShutdownHook());
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
        throw new UnsupportedOperationException("No ServletHolder");
    }

}
