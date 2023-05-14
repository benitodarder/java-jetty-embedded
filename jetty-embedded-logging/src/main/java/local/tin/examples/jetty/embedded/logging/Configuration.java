package local.tin.examples.jetty.embedded.logging;

import local.tin.examples.jetty.embedded.logging.controllers.UpperCaserCtrl;
import local.tin.examples.jetty.embedded.logging.web.JettyyLoggingShutdownHook;
import local.tin.examples.jetty.embedded.logging.web.filters.LoggingFilter;
import local.tin.tests.jetty.embedded.core.base.controllers.Ping;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import local.tin.tests.jetty.embedded.core.base.ILoggingConfiguration;

/**
 *
 * @author benitodarder
 */
public class Configuration implements ILoggingConfiguration {

    public static final String PATH_SPEC = "/*";
    public static final String SERVER_APPLICATION_PATH = "/path";
    public static final int SERVER_HTTP_PORT = 8080;
    
    @Override
    public String getURLPattern() {
        return PATH_SPEC;
    }

    @Override
    public String getApplicationPath() {
        return SERVER_APPLICATION_PATH;
    }

    @Override
    public int getHttpPort() {
        return SERVER_HTTP_PORT;
    }

    @Override
    public String getControllers() {
        return Ping.class.getCanonicalName() + ";" + UpperCaserCtrl.class.getCanonicalName() + ";" + MultiPartFeature.class.getCanonicalName();
    }

    @Override
    public ShutdownHook getShutdownHook() {
        return new JettyyLoggingShutdownHook();
    }

    @Override
    public Class getLoggingFilterClass() {
        return LoggingFilter.class;
    }
    
    @Override
    public String getLogginfFilterPath() {
        return "/*";
    }
    
    @Override
    public String getHttpAccessLogger() {
        return "httpAccessLogger";
    }
    
    @Override
    public String getHttpAccessPattern() {
        return "%{client}a - %u %{yyyy-MM-dd'T'HH:mm:ss.SSSZ}t %m %{ms}Tms \"%U\" %s %O \"%{Referer}i\" \"%{User-Agent}i\"";
    }
}
