package local.tin.tests.jetty.embedded.core.dao.impl;

import javax.persistence.EntityManagerFactory;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException;

/**
 *
 * @author benitodarder
 */
public abstract class AbstractNamedDAO<C0 extends local.tin.tests.jetty.embedded.core.models.domain.interfaces.INamed, C1 extends local.tin.tests.jetty.embedded.core.models.data.interfaces.INamed> extends AbstractEnableableDAO<C0, C1>{
     
    protected AbstractNamedDAO(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    protected C1 updateDataCommonFields(C0 domainObject, C1 dataObject) throws DAOException {
        dataObject.setName(domainObject.getName());
        return super.updateDataCommonFields(domainObject, dataObject); 
    }

    @Override
    protected C0 updateDomainCommonFields(C0 domainObject, C1 dataObject) throws DAOException {
        domainObject.setName(dataObject.getName());
        return super.updateDomainCommonFields(domainObject, dataObject); 
    }
    
    
    
}
