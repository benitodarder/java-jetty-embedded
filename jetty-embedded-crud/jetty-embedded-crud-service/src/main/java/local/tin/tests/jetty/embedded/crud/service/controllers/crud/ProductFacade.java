package local.tin.tests.jetty.embedded.crud.service.controllers.crud;

import javax.ws.rs.Path;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.ServiceException;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Product;
import local.tin.tests.jetty.embedded.crud.service.crud.impl.AbstractCRUDService;
import local.tin.tests.jetty.embedded.crud.service.crud.impl.ProductCRUD;
import local.tin.tests.jetty.embedded.crud.service.crud.impl.ProductCRUDServiceFactory;



/**
 *
 * @author benitodarder
 */
@Path("/crud/product")
public class ProductFacade extends AbstractCRUDResource<Product> {
    
    private final ProductCRUD crud;

    public ProductFacade() throws ServiceException {
        crud = (ProductCRUD) ProductCRUDServiceFactory.getInstance().getCRUD(Product.class);
    }
  
    @Override
    protected Class<Product> getCRUDClass() {
        return Product.class;
    }

    @Override
    protected AbstractCRUDService getCRUDService() {
        return crud;
    }
    
}
