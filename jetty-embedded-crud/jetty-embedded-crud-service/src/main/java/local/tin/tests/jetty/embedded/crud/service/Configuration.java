package local.tin.tests.jetty.embedded.crud.service;

import local.tin.tests.jetty.embedded.core.base.IConfiguration;
import local.tin.tests.jetty.embedded.core.base.controllers.Ping;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import local.tin.tests.jetty.embedded.crud.service.controllers.crud.AssemblyFacade;
import local.tin.tests.jetty.embedded.crud.service.controllers.crud.ComponentFacade;
import local.tin.tests.jetty.embedded.crud.service.controllers.crud.ProductFacade;
import local.tin.tests.jetty.embedded.crud.service.controllers.crud.UnitFacade;
import local.tin.tests.jetty.embedded.crud.service.web.ProductShutdownHook;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author benitodarder
 */
public class Configuration implements IConfiguration {

    public static final String PATH_SPEC = "/*";
    public static final String SERVER_APPLICATION_PATH = "/products";
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
        return Ping.class.getCanonicalName() + ";"
                + AssemblyFacade.class.getCanonicalName() + ";"
                + ComponentFacade.class.getCanonicalName() + ";"
                + ProductFacade.class.getCanonicalName() + ";"
                + UnitFacade.class.getCanonicalName() + ";" 
                + MultiPartFeature.class.getCanonicalName();
                
    }

    @Override
    public ShutdownHook getShutdownHook() {
        return new ProductShutdownHook();
    }

}
