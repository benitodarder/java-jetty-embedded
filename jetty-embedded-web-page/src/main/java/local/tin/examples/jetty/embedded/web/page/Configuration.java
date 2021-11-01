package local.tin.examples.jetty.embedded.web.page;

import java.io.File;
import local.tin.examples.jetty.embedded.web.page.controllers.WebPageCtrl;
import local.tin.examples.jetty.embedded.web.page.web.WebPageShutdownHook;
import local.tin.tests.jetty.embedded.core.base.controllers.Ping;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import local.tin.tests.jetty.embedded.core.base.IWebPageConfiguration;

/**
 *
 * @author benitodarder
 */
public class Configuration implements IWebPageConfiguration {

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
        return Ping.class.getCanonicalName() + ";" + WebPageCtrl.class.getCanonicalName() + ";" + MultiPartFeature.class.getCanonicalName();
    }

    @Override
    public ShutdownHook getShutdownHook() {
        return new WebPageShutdownHook();
    }
    
    @Override
    public String getResourceBase() {
        return  "." + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "public";
    }

    @Override
    public String getDocumentBase() {
        return "index.html";
    }
}
