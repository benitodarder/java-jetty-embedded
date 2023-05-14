package local.tin.tests.jetty.embedded.core.base.web;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.ILoggingConfiguration;
import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;
import local.tin.tests.jetty.embedded.core.base.IWebPageConfiguration;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.CustomRequestLog;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.Slf4jRequestLogWriter;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 *
 * @author benitodarder
 */
public abstract class AbstractJettyGralServer extends AbstractJettyServer {

    public AbstractJettyGralServer(IConfiguration configuration) {
        super(configuration);

        if (configuration instanceof ISSLConfiguration) {
            ISSLConfiguration iSSLConfiguration = (ISSLConfiguration) configuration;
            HttpConfiguration https = new HttpConfiguration();
            https.addCustomizer(new SecureRequestCustomizer());

            SslContextFactory sslContextFactory = new SslContextFactory();
            sslContextFactory.setKeyStorePath(iSSLConfiguration.getJKSPath());
            sslContextFactory.setKeyStorePassword(iSSLConfiguration.getKeystorePassword());
            sslContextFactory.setKeyManagerPassword(iSSLConfiguration.getJKSPassword());

            ServerConnector sslConnector = new ServerConnector(getJettyServer(),
                    new SslConnectionFactory(sslContextFactory, "http/1.1"),
                    new HttpConnectionFactory(https));
            sslConnector.setPort(8443);
            getJettyServer().setConnectors(new Connector[]{sslConnector});
        }

        if (configuration instanceof IWebPageConfiguration) {
            IWebPageConfiguration iWebPageConfiguration = (IWebPageConfiguration) configuration;
            ResourceHandler resH = new ResourceHandler();
            resH.setDirectoriesListed(true);
            resH.setWelcomeFiles(new String[]{iWebPageConfiguration.getDocumentBase()});
            resH.setResourceBase(iWebPageConfiguration.getResourceBase());
            ContextHandler resCtx = new ContextHandler();
            resCtx.setHandler(resH);

            ContextHandlerCollection handlers = new ContextHandlerCollection(resCtx, getServletContextHandler());
            getJettyServer().setHandler(handlers);
        }

        if (configuration instanceof ILoggingConfiguration) {
            ILoggingConfiguration iLoggingConfiguration = (ILoggingConfiguration) configuration;
            getServletContextHandler().addFilter(iLoggingConfiguration.getLoggingFilterClass(), iLoggingConfiguration.getLogginfFilterPath(), EnumSet.of(DispatcherType.REQUEST));
            Slf4jRequestLogWriter slfjRequestLogWriter = new Slf4jRequestLogWriter();
            slfjRequestLogWriter.setLoggerName(iLoggingConfiguration.getHttpAccessLogger());
            CustomRequestLog customRequestLog = new CustomRequestLog(slfjRequestLogWriter, iLoggingConfiguration.getHttpAccessPattern());
            getJettyServer().setRequestLog(customRequestLog);
        }

    }

}
