package local.tin.tests.jetty.embedded.crud.service.crud.impl;


import org.slf4j.Logger;
import local.tin.tests.jetty.embedded.core.dao.impl.DAOFactory;
import local.tin.tests.jetty.embedded.crud.dao.impl.ProductDAOFactory;
import org.slf4j.LoggerFactory;

/**
 *
 * @author benitodarder
 */
public class ProductCRUDServiceFactory extends AbstractCRUDServiceFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCRUDServiceFactory.class.getCanonicalName());

    private ProductCRUDServiceFactory(DAOFactory baseDAOFactory) {
        super(baseDAOFactory);
    }

    public static ProductCRUDServiceFactory getInstance() {
        return ProductCRUDServiceFactoryHolder.INSTANCE;
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    private static class ProductCRUDServiceFactoryHolder {

        private static final ProductCRUDServiceFactory INSTANCE = new ProductCRUDServiceFactory(ProductDAOFactory.getInstance());
    }
    
    @Override
    protected String getCRUDServicesPackage() {
        return "local.tin.tests.jetty.embedded.crud.service.crud.impl";
    }
    
}
