package local.tin.tests.jetty.embedded.core.dao.impl;

import javax.persistence.EntityManagerFactory;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException;

/**
 *
 * @author benitodarder
 */
public abstract class EnableableDAO<C0 extends local.tin.tests.jetty.embedded.core.models.domain.interfaces.IEnableable, C1 extends local.tin.tests.jetty.embedded.core.models.data.interfaces.IEnableable> extends IdentifiableDAO<C0, C1>{
     
    protected EnableableDAO(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    protected C1 updateDataCommonFields(C0 domainObject, C1 dataObject) throws DAOException {
        dataObject.setEnabled(domainObject.isEnabled());
        return super.updateDataCommonFields(domainObject, dataObject); 
    }

    @Override
    protected C0 updateDomainCommonFields(C0 domainObject, C1 dataObject) throws DAOException {
        domainObject.setEnabled(dataObject.isEnabled());
        return super.updateDomainCommonFields(domainObject, dataObject); 
    }
    
    
    
}
