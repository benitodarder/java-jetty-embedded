package local.tin.tests.jetty.embedded.core.base.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;
import static local.tin.tests.jetty.embedded.core.base.web.AbstractJettyServer.INIT_ORDER;
import static local.tin.tests.jetty.embedded.core.base.web.AbstractJettyServer.SERVER_PROVIDER_CLASSES;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 *
 * @author benitodarder
 */
public abstract class AbstractJettySSLServer extends AbstractJettyServer {

    private final Server jettyServer;
    private final ServletHolder jerseyServlet;

    public AbstractJettySSLServer(ISSLConfiguration configuration) {

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

    @Override
    protected Server getServer() {
        return jettyServer;
    }

    @Override
    protected ServletHolder getServleHolder() {
        return jerseyServlet;
    }

}
