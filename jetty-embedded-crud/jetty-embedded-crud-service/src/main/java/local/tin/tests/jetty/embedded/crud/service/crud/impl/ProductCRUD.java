package local.tin.tests.jetty.embedded.crud.service.crud.impl;

import local.tin.tests.jetty.embedded.core.dao.impl.AbstractDAO;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Product;


/**
 *
 * @author benitodarder
 */
public class ProductCRUD extends AbstractCRUDService<Product> {

    public ProductCRUD(AbstractDAO dao) {
        super(dao);
    }
  
    
    
}
