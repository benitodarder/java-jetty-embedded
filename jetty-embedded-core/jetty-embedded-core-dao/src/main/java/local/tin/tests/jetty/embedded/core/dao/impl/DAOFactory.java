package local.tin.tests.jetty.embedded.core.dao.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException;

/**
 * Must provide an entity manager factory
 * 
 * @author benito.darder
 */
public abstract class DAOFactory {
     
    protected abstract EntityManagerFactory getEntityManagerFactory();
    
    protected abstract String getDAOBasePackage() throws DAOException;
    
    protected abstract Logger getLogger();
    
    public void closeEntityManagerFactory() {
        getEntityManagerFactory().close();
    }
    
    public IdentifiableDAO getDAO(Class<?> klass) throws DAOException   {
        try {
            String daoName = getDAOFullName(klass);
            Class<?> daoClass = Class.forName(daoName);
            Constructor<?> constructor = daoClass.getDeclaredConstructor(EntityManagerFactory.class);
            return (IdentifiableDAO) constructor.newInstance(getEntityManagerFactory());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException ex) {
            getLogger().log(Level.SEVERE, ex.getMessage());
            throw new DAOException(ex);
        }  
    }  
    
    private String getDAOFullName(Class<?> klass) throws DAOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getDAOBasePackage()).append(".").append(klass.getSimpleName()).append("DAO");
        return stringBuilder.toString();
    }    
    
}
