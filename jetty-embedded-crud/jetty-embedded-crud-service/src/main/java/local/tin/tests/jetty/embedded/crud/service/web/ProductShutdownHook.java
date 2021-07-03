package local.tin.tests.jetty.embedded.crud.service.web;


import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import local.tin.tests.jetty.embedded.crud.dao.impl.ProductDAOFactory;
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
