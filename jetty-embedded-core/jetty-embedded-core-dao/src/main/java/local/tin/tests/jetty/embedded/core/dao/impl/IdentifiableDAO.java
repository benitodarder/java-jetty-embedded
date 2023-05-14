package local.tin.tests.jetty.embedded.core.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import local.tin.tests.jetty.embedded.core.dao.interfaces.IAbstractDAO;
import local.tin.tests.jetty.embedded.core.dao.model.interfaces.IEmbeddable;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.ICompositeId;

/**
 *
 * @author benito.darder
 * @param <C0>
 * @param <C1>
 */
public abstract class IdentifiableDAO<C0 extends local.tin.tests.jetty.embedded.core.models.domain.interfaces.IIdentifiable, C1 extends local.tin.tests.jetty.embedded.core.dao.model.interfaces.IIdentifiable>  implements IAbstractDAO<C0> {
    
    
    public static final int DEFAULT_DEPTH_ENTITY = 1;
    public static final int DEFAULT_DEPTH_ID = 0;
    public static final String COULD_NOT_PARSE_STRING_INTO_JAVASQL_DATE = "Could not parse string into java.sql.Date";    
    public static final String UNEXPECTED_RUNTIME_EXCEPTION_MESSAGE_PREFIX = " unexpected RuntimeException: ";
    public static final String NOT_SUPPORTED_YET = "Not supported yet.";    
    private static final Logger LOGGER = Logger.getLogger(IdentifiableDAO.class.getCanonicalName());
    private final EntityManagerFactory entityManagerFactory;
    

    protected IdentifiableDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Returns the entity manager factory.
     *
     * @return EntityManagerFactory
     */
    protected EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    /**
     * Returns an EntityManager from the factory.
     *
     * @return EntityManager
     */
    protected EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    /**
     * Returns the class logger.
     *
     * @return Logger
     */
    protected Logger getLogger() {
        return LOGGER;
    }

    /**
     * Returns the class corresponding to the data model.
     *
     * @return Class
     */
    protected abstract Class<C1> getDAOClass();

    /**
     * Returns a new domain model inistance.
     * 
     * @return C0
     */
    protected abstract C0 getDomainObjectNewInstance() throws DAOException;
    
    /**
     * Returns a new data model inistance.
     * 
     * @return C1
     */
    protected abstract C1 getDataObjectNewInstance() throws DAOException;
    
    /**
     * Assigns depth 0 fields from data model to domain model
     * 
     * @param domainObject C0
     * @param dataObject C1
     * @return C0
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected abstract C0 updateDomainObjectDepth0Fields(C0 domainObject, C1 dataObject) throws DAOException;
    
    /**
     * Assigns fields from data model to domain model at given depth
     * 
     * @param domainObject C0
     * @param dataObject C1
     * @param depth int
     * @return C0
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected abstract C0 updateDomainObjectDeeperFields(C0 domainObject, C1 dataObject, int depth) throws DAOException;    
    
    /**
     * Assigns depth 0 fields from data model to domain model
     * 
     * @param domainObject C0
     * @param dataObject C1
     * @return C1
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected abstract C1 updateDataObjectDepth0Fields(C0 domainObject, C1 dataObject) throws DAOException;
    
    /**
     * Assigns fields from data model to domain model at given depth
     * 
     * @param domainObject C0
     * @param dataObject C1
     * @param depth int
     * @return C1
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected abstract C1 updateDataObjectDeeperFields(C0 domainObject, C1 dataObject, int depth) throws DAOException;      

    /**
     * Assigns common fields.
     * 
     * @param domainObject C0
     * @param dataObject C1
     * @return C0
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected C0 updateDomainCommonFields(C0 domainObject, C1 dataObject) throws DAOException {
        
        return updateDomainObjectId(dataObject, domainObject);
    }
    
    /**
     * Assigns common fields.
     * 
     * @param domainObject C0
     * @param dataObject C1
     * @return C0
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected C1 updateDataCommonFields(C0 domainObject, C1 dataObject) throws DAOException {
        
        return updateDataObjectId(dataObject, domainObject);
    }    
    
    /**
     * Returns a domain object filled with depth.
     *
     * <ol>
     * <li>Depth 0, primitives</li>
     * <li>Depth 1, depth 0 and first level attributes at depth 0</li>
     * <li>Depth 2, depth 1 where the its first level attributes are also
     * filled</li>
     * <li>...</li>
     * </ol>
     *
     * @param dataObject Data class object
     * @param depth
     * @return Domain class object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    public C0 getDomainObject(C1 dataObject, int depth) throws DAOException {
        C0 domainObject = getDomainObjectNewInstance();
        updateDomainObjectDepth0Fields(domainObject, dataObject);
        if (depth > 0) {
            updateDomainObjectDeeperFields(domainObject, dataObject, depth - 1);
        }
        return updateDomainCommonFields(domainObject, dataObject);
    }

    /**
     * Returns a data object filled at depth 1.
     *
     * <ol>
     * <li>Depth 0, primitives</li>
     * <li>Depth 1, depth 0 and first level attributes at depth 0</li>
     * <li>Depth 2, depth 1 where the its first level attributes are also
     * filled</li>
     * <li>...</li>
     * </ol>
     *
     * Currently this parameter is ignored, while pending to check suitability
     * 
     * @param domainObject Domain class object
     * @param depth
     * @return Domain class object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    public C1 getDataObject(C0 domainObject, int depth) throws DAOException {
        C1 dataObject = getDataObjectNewInstance();
        updateDataObjectDepth0Fields(domainObject, dataObject);
        if (depth > 0) {
            updateDataObjectDeeperFields(domainObject, dataObject, depth - 1);
        }
        return updateDataCommonFields(domainObject, dataObject);
    }

    /**
     * Updates auto generated identifier in domain class. From the data model
     * object to the domain model object
     *
     * Returns the object given as attribute.
     *
     * @param dataObject Data model object
     * @param domainObject Domain model object
     * @return C0 Domain model object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected C0 updateDomainObjectId(C1 dataObject, C0 domainObject) throws DAOException {
        if (dataObject.getId() instanceof IEmbeddable) {
            domainObject.setId(getCompositedId((IEmbeddable) dataObject.getId()));
        } else {
            domainObject.setId(dataObject.getId());
        }
        return domainObject;
    }

    /**
     * Updates auto generated identifier in domain class. From the data model
     * object to the domain model object
     *
     * Returns the object given as attribute.
     *
     * @param dataObject Data model object
     * @param domainObject Domain model object
     * @return C0 Domain model object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected C1 updateDataObjectId(C1 dataObject, C0 domainObject) throws DAOException {
        if (domainObject.getId() instanceof ICompositeId) {
            dataObject.setId(getEmmbeddedId((ICompositeId) domainObject.getId()));
        } else {
            dataObject.setId(domainObject.getId());
        }
        return dataObject;
    }    
    
    /**
     * Creates the corresponding IEmbeddable from the given ICompositeId.
     * This ICompositeId matches the DAO class:
     *
     * @param domainObject ICompositeId
     * @return IEmbedabble
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected IEmbeddable getEmmbeddedId(ICompositeId domainObject) throws DAOException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET + " in class " + getDAOClass().getCanonicalName());
    }

    /**
     * Creates the corresponding ICompositeId from the given IEmbeddable.
     * This ICompositeId matches the DAO class:
     *
     * @param dataObject IEmbeddable
     * @return ICompositeId
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected ICompositeId getCompositedId(IEmbeddable dataObject) throws DAOException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET + " in class " + getDAOClass().getCanonicalName());
    }
    
    /**
     * Returns a list of entities matching the given parameters.
     *
     * @param entityManager
     * @param parameters Map of String keys and Object values
     * @return List of Data class model
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected List<C1> findByParameters(EntityManager entityManager, Map<String, Object> parameters) throws DAOException {
        List<C1> resultList = new ArrayList<>();
        String queryString = null;
        try {
            queryString = getQueryStringWithParameters(parameters);
            Query typedQuery = entityManager.createQuery(queryString);
            if (parameters != null && !parameters.isEmpty()) {
                for (Map.Entry<String, Object> current : parameters.entrySet()) {
                    typedQuery.setParameter(current.getKey(), current.getValue());
                }
            }
            resultList = typedQuery.getResultList();
        } catch (IllegalArgumentException iae) {
            throw new DAOException(getExceptionMessage(iae) + ", for query string: " + queryString, iae);
        }
        return resultList;
    }

    /**
     * Returns a list of all entities.
     *
     * @param entityManager
     * @return List of Data class model
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected List<C1> findAll(EntityManager entityManager) throws DAOException {
        return findByParameters(entityManager, null);
    }

    private String getQueryStringWithParameters(Map<String, Object> parameters) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select e from ").append(getDAOClass().getSimpleName()).append(" e");
        if (parameters != null && !parameters.isEmpty()) {
            stringBuilder.append(" where");
            boolean firstDone = false;
            for (String current : parameters.keySet()) {
                if (firstDone) {
                    stringBuilder.append(" and");
                }
                stringBuilder.append(" e.").append(current).append(" = :").append(current.replaceAll("\\.", ""));
                firstDone = true;
            }

        }
        return stringBuilder.toString();
    }

    /**
     * Persists the data class in the entity manager.
     *
     * @param entityManager
     * @param dataClass Data class object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    
    protected void persist(EntityManager entityManager, C1 dataClass) throws DAOException {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(dataClass);
            entityTransaction.commit();
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException | IllegalStateException e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    /**
     * Returns the default exception message.
     *
     * @param e RuntimeException
     * @return String
     */
    protected String getExceptionMessage(RuntimeException e) {
        return getClass() + UNEXPECTED_RUNTIME_EXCEPTION_MESSAGE_PREFIX + e.getLocalizedMessage();
    }

    /**
     * Merges/Updates the data class using the given entity manager.
     *
     * @param entityManager
     * @param dataClass Data class object
     * @return Data class object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected C1 merge(EntityManager entityManager, C1 dataClass) throws DAOException {
        EntityTransaction entityTransaction = null;
        C1 iunit = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            iunit = entityManager.merge(dataClass);
            entityTransaction.commit();
        } catch (IllegalArgumentException | TransactionRequiredException | IllegalStateException e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            throw new DAOException(e);
        }
        return iunit;
    }

    /**
     * Refreshes the data class using the given entity manager.
     *
     * @param entityManager
     * @param dataClass Data class object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected void refresh(EntityManager entityManager, C1 dataClass) throws DAOException {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.refresh(dataClass);
            entityTransaction.commit();
        } catch (IllegalArgumentException | PersistenceException | IllegalStateException e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    /**
     * Removes the data class attached to the given entity manager.Merges the
 object before removal.
     *
     * @param entityManager
     * @param dataClass Data class object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected void remove(EntityManager entityManager, C1 dataClass) throws DAOException {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            C1 merged = entityManager.merge(dataClass);
            entityManager.remove(merged);
            entityTransaction.commit();
        } catch (IllegalArgumentException | TransactionRequiredException | IllegalStateException e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            throw new DAOException(e);
        }
    }

    /**
     * Retrieves the entity by id. Returns null if not found.
     *
     * @param entityManager
     * @param id Object
     * @return Data class object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    protected C1 findById(EntityManager entityManager, Object id) throws DAOException {
        C1 iunit = null;
        try {
            iunit = entityManager.find(getDAOClass(), getEmbeddedIdWhenNeeded(id));
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new DAOException(e);
        }
        return iunit;
    }

    private Object getEmbeddedIdWhenNeeded(Object id) throws DAOException {
        if (id instanceof ICompositeId) {
            return getEmmbeddedId((ICompositeId) id);
        }
        return id;
    }

    @Override
    public C0 create(C0 objectModel) throws DAOException {
        C1 dataClass = getDataObject(objectModel, DEFAULT_DEPTH_ENTITY);
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            persist(entityManager, dataClass);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return updateDomainObjectId(dataClass, objectModel);
    }

    @Override
    public C0 retrieve(Object id) throws DAOException {
        C0 c0 = null;
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            C1 c1 = findById(entityManager, getEmbeddedIdWhenNeeded(id));
            if (c1 != null) {
                c0 = getDomainObject(c1, DEFAULT_DEPTH_ENTITY);
            } 
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return c0;
    }

    @Override
    public C0 update(C0 objectModel) throws DAOException {
        C0 c0 = null;
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            C1 c1 = getDataObject(objectModel, DEFAULT_DEPTH_ENTITY);
            c1 = merge(entityManager, c1);
            c0 = getDomainObject(c1, DEFAULT_DEPTH_ENTITY);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return c0;
    }

    @Override
    public void delete(C0 objectModel) throws DAOException {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            C1 c1 = findById(entityManager, getEmbeddedIdWhenNeeded(objectModel.getId()));
            if (c1 != null) {
                remove(entityManager, c1);
            } else {
                throw new DAOException("Could not find element of: " + getDAOClass().getCanonicalName() + " with id: " + objectModel.getId());
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<C0> retrieveAll() throws DAOException {
        return retrieveAllByParameters(null);
    }
    
    @Override
    public List<C0> retrieveAllByParameters(Map<String, Object> parameters) throws DAOException {
        List<C0> results = new ArrayList<>();
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            List<C1> dataModelResults = findByParameters(entityManager, parameters);
            results = getListOfDomainObjects(dataModelResults);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }            
        }
        return results;
    }

    private List<C0> getListOfDomainObjects(List<C1> dataModelResults) throws DAOException {
        List<C0> results = new ArrayList<>();
        Iterator<C1> iterator = dataModelResults.iterator();
        while (iterator.hasNext()) {
            C1 current = iterator.next();
            results.add(getDomainObject(current, DEFAULT_DEPTH_ENTITY));
            iterator.remove();
        }
        return results;
    }    

    
}
