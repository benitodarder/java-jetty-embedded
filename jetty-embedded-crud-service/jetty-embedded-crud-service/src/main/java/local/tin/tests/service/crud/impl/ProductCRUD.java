package local.tin.tests.service.crud.impl;


import local.tin.tests.model.domain.product.Product;
import local.tin.tests.dao.impl.AbstractDAO;

/**
 *
 * @author benitodarder
 */
public class ProductCRUD extends AbstractCRUDService<Product> {

    public ProductCRUD(AbstractDAO dao) {
        super(dao);
    }
  
    
    
}
