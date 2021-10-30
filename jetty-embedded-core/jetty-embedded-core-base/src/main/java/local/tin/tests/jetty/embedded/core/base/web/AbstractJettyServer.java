package local.tin.tests.jetty.embedded.core.base.web;

import java.util.Map;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;
import local.tin.tests.jetty.embedded.core.base.web.interfaces.IAbstractJettyServer;
import org.eclipse.jetty.server.Server;
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

    protected abstract Server getServer();
    protected abstract ServletHolder getServleHolder();

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
            getServer().start();
            getServer().join();
        } catch (Exception ex) {
            throw new JettyEmbeddedCommonException(ex);
        }
    }

    @Override
    public void stopServer() throws JettyEmbeddedCommonException {
        try {
            getServer().stop();
        } catch (Exception ex) {
            throw new JettyEmbeddedCommonException(ex);
        }
    }

    @Override
    public void destroyServer() throws JettyEmbeddedCommonException {
        getServer().destroy();
    }

    @Override
    public Map<String, String> getInitParameters() {
        return getServleHolder().getInitParameters();
    }    
}
