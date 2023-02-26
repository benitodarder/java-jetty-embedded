package local.tin.tests.jetty.embedded.crud.dao;

import java.util.HashMap;
import java.util.Map;
import local.tin.tests.jetty.embedded.core.dao.DAOConfiguration;
import org.apache.log4j.Logger;

/**
 *
 * @author benito.darder
 */
public class ProductDAOConfiguration extends DAOConfiguration {

    private static final Logger LOGGER = Logger.getLogger(ProductDAOConfiguration.class);
    private static Map<String, String> configurationMap;

    private ProductDAOConfiguration() {
    }

    public static synchronized ProductDAOConfiguration getInstance() {

        if (configurationMap == null) {
            configurationMap = new HashMap<>();
        }
        return DAOConfigurationConfigurationHolder.INSTANCE;
    }

    private static class DAOConfigurationConfigurationHolder {

        private static final ProductDAOConfiguration INSTANCE = new ProductDAOConfiguration();

        private DAOConfigurationConfigurationHolder() {
        }
    }

    @Override
    public Map<String, String> getConfigurationMap() {
        return configurationMap;
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

}
