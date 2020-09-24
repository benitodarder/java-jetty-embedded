package local.tin.tests.service.crud.impl;


import local.tin.tests.model.domain.product.Unit;
import local.tin.tests.dao.impl.AbstractDAO;

/**
 *
 * @author benitodarder
 */
public class UnitCRUD extends AbstractCRUDService<Unit> {

    public UnitCRUD(AbstractDAO dao) {
        super(dao);
    }
   
}
