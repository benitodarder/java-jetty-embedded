package local.tin.tests.jetty.embedded.core.dao.impl;

import javax.persistence.EntityManagerFactory;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException;

/**
 *
 * @author benitodarder
 * @param <C0>
 * @param <C1>
 */
public abstract class NamedEnableableDAO<C0 extends local.tin.tests.jetty.embedded.core.models.domain.interfaces.INamedEnableable, C1 extends local.tin.tests.jetty.embedded.core.dao.model.interfaces.INamedEnableable> extends EnableableDAO<C0, C1>{
     
    protected NamedEnableableDAO(EntityManagerFactory entityManagerFactory) {
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
