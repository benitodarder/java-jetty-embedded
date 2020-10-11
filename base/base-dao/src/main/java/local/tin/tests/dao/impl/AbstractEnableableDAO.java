package local.tin.tests.dao.impl;

import javax.persistence.EntityManagerFactory;
import local.tin.tests.model.domain.exceptions.DAOException;

/**
 *
 * @author benitodarder
 */
public abstract class AbstractEnableableDAO<C0 extends local.tin.tests.model.domain.interfaces.IEnableable, C1 extends local.tin.tests.model.data.interfaces.IEnableable> extends AbstractDAO<C0, C1>{
     
    public AbstractEnableableDAO(EntityManagerFactory entityManagerFactory) {
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
