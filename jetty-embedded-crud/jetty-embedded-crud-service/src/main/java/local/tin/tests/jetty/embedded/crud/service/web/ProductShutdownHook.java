package local.tin.tests.jetty.embedded.crud.service.web;


import java.util.logging.Logger;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import local.tin.tests.jetty.embedded.crud.dao.impl.ProductDAOFactory;

/**
 *
 * @author benitodarder
 */
public class ProductShutdownHook extends ShutdownHook {

    private static final Logger LOGGER = Logger.getLogger(ProductShutdownHook.class.getCanonicalName());
    
    @Override
    protected void doShutdown() {
        ProductDAOFactory.getInstance().closeEntityManagerFactory();
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
   
    
}
