package local.tin.examples.jetty.embedded.camelCaser;

import local.tin.examples.jetty.embedded.camelCaser.controllers.CamelCaserCtrl;
import local.tin.examples.jetty.embedded.camelCaser.web.HelloWorldShutdownHook;
import local.tin.tests.jetty.embedded.core.base.controllers.Ping;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import local.tin.tests.jetty.embedded.core.base.ISSLConfiguration;

/**
 *
 * @author benitodarder
 */
public class Configuration implements ISSLConfiguration {

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
        return Ping.class.getCanonicalName() + ";" + CamelCaserCtrl.class.getCanonicalName() + ";" + MultiPartFeature.class.getCanonicalName();
    }

    @Override
    public ShutdownHook getShutdownHook() {
        return new HelloWorldShutdownHook();
    }

    @Override
    public String getJKSPath() {
        return Configuration.class.getResource("/camelCaser.jks").getPath();
    }

    @Override
    public String getKeystorePassword() {
        return "password";
    }

    @Override
    public String getJKSPassword() {
        return "password";
    }
}
