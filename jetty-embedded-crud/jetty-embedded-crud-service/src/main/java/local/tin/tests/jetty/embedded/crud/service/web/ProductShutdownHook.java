package local.tin.tests.jetty.embedded.crud.service.web;


import org.slf4j.Logger;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import local.tin.tests.jetty.embedded.crud.dao.impl.ProductDAOFactory;
import org.slf4j.LoggerFactory;

/**
 *
 * @author benitodarder
 */
public class ProductShutdownHook extends ShutdownHook {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductShutdownHook.class.getCanonicalName());
    
    @Override
    protected void doShutdown() {
        ProductDAOFactory.getInstance().closeEntityManagerFactory();
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
   
    
}
