package local.tin.tests.jetty.embedded.core.base.web;

import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;
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


    public AbstractJettySSLServer(ISSLConfiguration configuration) {
        super(configuration);
        
        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());

        SslContextFactory sslContextFactory = new SslContextFactory();
        getLogger().debug("AbstractJettySSLServer JKS path:" + configuration.getJKSPath());
        sslContextFactory.setKeyStorePath(configuration.getJKSPath());
        sslContextFactory.setKeyStorePassword(configuration.getKeystorePassword());
        sslContextFactory.setKeyManagerPassword(configuration.getJKSPassword());

        ServerConnector sslConnector = new ServerConnector(getJettyServer(),
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https));
        sslConnector.setPort(8443);
        getJettyServer().setConnectors(new Connector[]{sslConnector});

    }


}
