package local.tin.tests.jetty.embedded.crud.service.crud.impl;

import local.tin.tests.jetty.embedded.core.dao.impl.IdentifiableDAO;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Assembly;


/**
 *
 * @author benitodarder
 */
public class AssemblyCRUD extends AbstractCRUDService<Assembly> {

    public AssemblyCRUD(IdentifiableDAO dao) {
        super(dao);
    }
    
}
