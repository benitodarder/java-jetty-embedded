package local.tin.tests.jetty.embedded.crud.service;

import local.tin.tests.jetty.embedded.core.utils.files.ResourcesUtils;
import local.tin.tests.jetty.embedded.crud.dao.ProductDAOConfiguration;
import local.tin.tests.jetty.embedded.crud.service.web.ProductServer;

/**
 *
 * @author benitodarder
 */
public class Application {

    public static final String PROPERTIES_FILE = "products-service.properties";

    public static void main(String[] args) throws Exception {
        ProductServer productServer = new ProductServer(new Configuration());

        ResourcesUtils resourcesUtils = ResourcesUtils.getInstance();
        ProductDAOConfiguration.getInstance().loadProperties(resourcesUtils.getPropertiesFile(Application.class, PROPERTIES_FILE));

        try {
            productServer.startAndJoinServer();
        } finally {
            productServer.stopServer();
            productServer.destroyServer();
        }
    }

}
