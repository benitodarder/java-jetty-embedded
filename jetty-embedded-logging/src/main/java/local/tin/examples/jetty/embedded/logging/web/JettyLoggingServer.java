package local.tin.examples.jetty.embedded.logging.web;

import local.tin.examples.jetty.embedded.logging.web.filters.LoggingFilter;
import java.util.EnumSet;
import java.util.Map;
import javax.servlet.DispatcherType;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.exceptions.JettyEmbeddedCommonException;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyNoSSLServer;
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
public class JettyLoggingServer extends AbstractJettyNoSSLServer {

    private static final Logger LOGGER = Logger.getLogger(JettyLoggingServer.class);

    public JettyLoggingServer(IConfiguration parameters) {

        super(parameters);
        
        getServletContextHandler().addFilter(LoggingFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        Slf4jRequestLogWriter slfjRequestLogWriter = new Slf4jRequestLogWriter();
        slfjRequestLogWriter.setLoggerName("appenderLogger");
        String format = "%{client}a - %u %{yyyy-MM-dd'T'HH:mm:ss.SSSZ}t %m %{ms}Tms \"%U\" %s %O \"%{Referer}i\" \"%{User-Agent}i\"";
        CustomRequestLog customRequestLog = new CustomRequestLog(slfjRequestLogWriter, format);
        getJettyServer().setRequestLog(customRequestLog);
                     
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
