package local.tin.examples.jetty.embedded.logging.web;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import local.tin.examples.jetty.embedded.logging.Configuration;
import org.apache.log4j.Logger;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyNoSSLServer;
import org.eclipse.jetty.server.CustomRequestLog;
import org.eclipse.jetty.server.Slf4jRequestLogWriter;

/**
 *
 * @author benitodarder
 */
public class JettyLoggingServer extends AbstractJettyNoSSLServer {

    private static final Logger LOGGER = Logger.getLogger(JettyLoggingServer.class);

    public JettyLoggingServer(Configuration parameters) {
        
        super(parameters);
        
        getServletContextHandler().addFilter(parameters.getLoggingFilterClass(), parameters.getLogginfFilterPath(), EnumSet.of(DispatcherType.REQUEST));
        Slf4jRequestLogWriter slfjRequestLogWriter = new Slf4jRequestLogWriter();
        slfjRequestLogWriter.setLoggerName(parameters.getHttpAccessLogger());
        CustomRequestLog customRequestLog = new CustomRequestLog(slfjRequestLogWriter, parameters.getHttpAccessPattern());
        getJettyServer().setRequestLog(customRequestLog);
                     
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
