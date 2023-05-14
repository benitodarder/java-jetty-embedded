package local.tin.examples.jetty.embedded.camelCaser;

import local.tin.examples.jetty.embedded.camelCaser.controllers.CamelCaserCtrl;
import local.tin.examples.jetty.embedded.camelCaser.web.HelloWorldShutdownHook;
import local.tin.examples.jetty.embedded.camelCaser.web.filters.RequestResponseLogger;
import local.tin.tests.jetty.embedded.core.base.controllers.Ping;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;
import local.tin.tests.java.embedded.core.slf4j.ILoggingConfiguration;
/**
 *
 * @author benitodarder
 */
public class Configuration implements ISSLConfiguration, ILoggingConfiguration {

    public static final String PATH_SPEC = "/*";
    public static final String SERVER_APPLICATION_PATH = "/";
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
        return Ping.class.getCanonicalName() + ";" + CamelCaserCtrl.class.getCanonicalName() + ";" + MultiPartFeature.class.getCanonicalName();
    }

    @Override
    public ShutdownHook getShutdownHook() {
        return new HelloWorldShutdownHook();
    }

    @Override
    public String getJKSPath() {
        return "camelCaser.jks";
    }

    @Override
    public String getKeystorePassword() {
        return "password";
    }

    @Override
    public String getJKSPassword() {
        return "password";
    }

    @Override
    public Class getLoggingFilterClass() {
        return RequestResponseLogger.class;
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
