package local.tin.tests.service.crud.impl;

import local.tin.tests.model.domain.product.Component;
import local.tin.tests.dao.impl.AbstractDAO;

/**
 *
 * @author benitodarder
 */
public class ComponentCRUD extends AbstractCRUDService<Component>{

    public ComponentCRUD(AbstractDAO dao) {
        super(dao);
    }
    
}
