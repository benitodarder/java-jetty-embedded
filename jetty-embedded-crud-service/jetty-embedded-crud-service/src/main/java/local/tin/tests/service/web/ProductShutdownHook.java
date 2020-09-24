package local.tin.tests.service.web;

import local.tin.tests.base.service.web.ShutdownHook;
import local.tin.tests.dao.impl.ProductDAOFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class ProductShutdownHook extends ShutdownHook {

    private static final Logger LOGGER = Logger.getLogger(ProductShutdownHook.class);
    
    @Override
    protected void doShutdown() {
        ProductDAOFactory.getInstance().closeEntityManagerFactory();
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
   
    
}
