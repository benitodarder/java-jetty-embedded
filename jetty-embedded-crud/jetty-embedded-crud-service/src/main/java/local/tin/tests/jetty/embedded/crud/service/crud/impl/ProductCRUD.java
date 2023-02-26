package local.tin.tests.jetty.embedded.crud.service.crud.impl;

import local.tin.tests.jetty.embedded.core.dao.impl.IdentifiableDAO;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Product;


/**
 *
 * @author benitodarder
 */
public class ProductCRUD extends AbstractCRUDService<Product> {

    public ProductCRUD(IdentifiableDAO dao) {
        super(dao);
    }
  
    
    
}
