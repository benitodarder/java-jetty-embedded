package local.tin.tests.jetty.embedded.crud.service.crud.impl;

import java.util.ArrayList;
import java.util.List;
import local.tin.tests.jetty.embedded.core.dao.impl.IdentifiableDAO;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.ServiceException;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IIdentifiable;



/**
 *
 * @author benitodarder
 * @param <C>
 */
public abstract class AbstractCRUDService<C extends IIdentifiable> {
    
    private final IdentifiableDAO dao;
    
    public AbstractCRUDService(IdentifiableDAO dao) {
        this.dao = dao;
    }
    
    public C create(C c) throws ServiceException {
        C result = null;
        try {
            result =  (C) dao.create(c);
        } catch (DAOException ex) {
            throw new ServiceException("Could not create: " + ex.getLocalizedMessage(), ex);
        }
        return result;
    }
    
    public C retrieve(Object id) throws ServiceException {
        try {
            return (C) dao.retrieve(id);
        } catch (DAOException ex) {
            throw new ServiceException("Could not retreive: " + ex.getLocalizedMessage(), ex);
        }
    }
    
    public C update(C c) throws ServiceException {
        C result = null;
        try {
            result = (C) dao.update(c);
        } catch (DAOException ex) {
            throw new ServiceException("Could not update: " + ex.getLocalizedMessage(), ex);
        }
        return result;        
    }   
    
    public void delete(C c) throws ServiceException {
        try {
            dao.delete(c);
        } catch (DAOException ex) {
            throw new ServiceException("Could not delete: " + ex.getLocalizedMessage(), ex);
        }
    }       

    public List<C> retrieveAll() throws ServiceException {
        List<C> result = new ArrayList<>();
        try {
            result = dao.retrieveAll();
        } catch (DAOException ex) {
            throw new ServiceException("Could not retrieve: " + ex.getLocalizedMessage(), ex);
        }
        return result;        
    }    
        
    
}
