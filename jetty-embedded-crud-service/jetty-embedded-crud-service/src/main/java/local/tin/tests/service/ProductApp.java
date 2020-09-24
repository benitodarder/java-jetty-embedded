package local.tin.tests.service;

import java.util.HashMap;
import java.util.Map;
import local.tin.tests.dao.ProductDAOConfiguration;

import local.tin.tests.service.facades.crud.AssemblyFacade;
import local.tin.tests.service.facades.crud.ComponentFacade;
import local.tin.tests.service.facades.crud.ProductFacade;

import local.tin.tests.service.facades.crud.UnitFacade;
import local.tin.tests.service.web.ProductServer;
import local.tin.tests.service.web.ProductShutdownHook;
import local.tin.tests.services.facades.Ping;
import local.tin.tests.utils.file.ResourcesUtils;

/**
 *
 * @author benitodarder
 */
public class ProductApp {

    public static final String PATH_SPEC = "/*";    
    public static final String SERVER_APPLICATION_PATH = "/products";
    public static final int SERVER_HTTP_PORT = 8080;
    public static final String PROPERTIES_FILE = "products-service.properties";
    
    public static void main(String[] args) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ProductServer.SERVER_PATH_PARAMETER, SERVER_APPLICATION_PATH);
        parameters.put(ProductServer.SERVER_HTTP_PORT_PARAMETER, SERVER_HTTP_PORT);
        parameters.put(ProductServer.PATH_SPEC, PATH_SPEC);
        parameters.put(ProductServer.SERVER_PROVIDER_CLASSES, Ping.class.getCanonicalName() + ";" 
                                                            + AssemblyFacade.class.getCanonicalName() + ";" 
                                                            + ComponentFacade.class.getCanonicalName() + ";" 
                                                            + ProductFacade.class.getCanonicalName() + ";" 
                                                            + UnitFacade.class.getCanonicalName()); 
//                                                            + JacksonJaxbJsonProvider.class.getCanonicalName() );   
        parameters.put(ProductServer.SERVER_SHUTOWN_HOOK, new ProductShutdownHook());
        ProductServer productServer = new ProductServer(parameters);

        ResourcesUtils resourcesUtils = ResourcesUtils.getInstance();
        ProductDAOConfiguration.getInstance().loadProperties(resourcesUtils.getPropertiesFile(ProductApp.class, PROPERTIES_FILE));
        
        try {
            productServer.run();
        } finally {
            productServer.destroy();
        }
    }

}
