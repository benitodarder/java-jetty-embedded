package local.tin.tests.jetty.embedded.crud.service.crud.impl;

import local.tin.tests.jetty.embedded.core.dao.impl.AbstractDAO;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Assembly;


/**
 *
 * @author benitodarder
 */
public class AssemblyCRUD extends AbstractCRUDService<Assembly> {

    public AssemblyCRUD(AbstractDAO dao) {
        super(dao);
    }
    
}
