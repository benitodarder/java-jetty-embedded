package local.tin.tests.jetty.embedded.core.base.web;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import local.tin.tests.jetty.embedded.core.base.ILoggingConfiguration;
import local.tin.tests.jetty.embedded.core.base.web.AbstractJettyServer;
import org.eclipse.jetty.server.CustomRequestLog;
import org.eclipse.jetty.server.Slf4jRequestLogWriter;

/**
 *
 * @author benitodarder
 */
public abstract class AbstractJettyLoggingServer extends AbstractJettyServer {

    public AbstractJettyLoggingServer(ILoggingConfiguration parameters) {
        
        super(parameters);
        
        getServletContextHandler().addFilter(parameters.getLoggingFilterClass(), parameters.getLogginfFilterPath(), EnumSet.of(DispatcherType.REQUEST));
        Slf4jRequestLogWriter slfjRequestLogWriter = new Slf4jRequestLogWriter();
        slfjRequestLogWriter.setLoggerName(parameters.getHttpAccessLogger());
        CustomRequestLog customRequestLog = new CustomRequestLog(slfjRequestLogWriter, parameters.getHttpAccessPattern());
        getJettyServer().setRequestLog(customRequestLog);
                     
    }
    
}
