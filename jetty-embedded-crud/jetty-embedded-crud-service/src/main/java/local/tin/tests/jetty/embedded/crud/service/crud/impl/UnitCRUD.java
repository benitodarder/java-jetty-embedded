package local.tin.tests.jetty.embedded.crud.service.crud.impl;

import local.tin.tests.jetty.embedded.core.dao.impl.AbstractDAO;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Unit;


/**
 *
 * @author benitodarder
 */
public class UnitCRUD extends AbstractCRUDService<Unit> {

    public UnitCRUD(AbstractDAO dao) {
        super(dao);
    }
   
}
