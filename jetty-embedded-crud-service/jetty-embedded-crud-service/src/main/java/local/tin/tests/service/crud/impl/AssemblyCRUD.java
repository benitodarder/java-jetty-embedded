package local.tin.tests.service.crud.impl;

import local.tin.tests.dao.impl.AbstractDAO;
import local.tin.tests.model.domain.product.Assembly;



/**
 *
 * @author benitodarder
 */
public class AssemblyCRUD extends AbstractCRUDService<Assembly> {

    public AssemblyCRUD(AbstractDAO dao) {
        super(dao);
    }
    
}
