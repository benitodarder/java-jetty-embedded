package local.tin.tests.jetty.embedded.crud.service.crud.impl;

import local.tin.tests.jetty.embedded.core.dao.impl.IdentifiableDAO;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Component;

/**
 *
 * @author benitodarder
 */
public class ComponentCRUD extends AbstractCRUDService<Component>{

    public ComponentCRUD(IdentifiableDAO dao) {
        super(dao);
    }
    
}
