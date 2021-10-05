package local.tin.examples.jetty.embedded.logging;

import local.tin.examples.jetty.embedded.logging.controllers.UpperCaserCtrl;
import local.tin.examples.jetty.embedded.logging.web.JettyyLoggingShutdownHook;
import local.tin.tests.jetty.embedded.core.base.controllers.Ping;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import local.tin.tests.jetty.embedded.core.base.IConfiguration;

/**
 *
 * @author benitodarder
 */
public class Configuration implements IConfiguration {

    public static final String PATH_SPEC = "/*";
    public static final String SERVER_APPLICATION_PATH = "/path";
    public static final int SERVER_HTTP_PORT = 8080;
    public static final String ACCESS_LOG = "access.log";
    public static final String MESSAGE_LOG = "message.log";
    
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

}
