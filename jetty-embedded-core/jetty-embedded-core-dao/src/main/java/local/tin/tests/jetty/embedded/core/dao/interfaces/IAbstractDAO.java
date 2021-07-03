package local.tin.tests.jetty.embedded.core.dao.interfaces;

import java.util.List;
import java.util.Map;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IIdentifiable;


/**
 *
 * @author benito.darder
 * @param <C0>
 */
public interface IAbstractDAO<C0 extends IIdentifiable> {
    
    /**
     * Saves the given entity into the database. Returns the object with the
     * identifier filled.
     *
     * @param domainObject Domain model object
     * @return domainObject Domain model object
     * @throws local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException
     */
    public C0 create(C0 domainObject) throws DAOException;

    /**
     * Returns the object corresponding to the identifier, o null if not
     * present.
     *
     * By default objects are returned at depth 1.
     *
     * Depth:
     *
     * <ol>
     * <li>Depth 0 only assigns class primitives and member classes with only
     * identifier. Collections are empty.</li>
     * <li>Depth 1 has member classes filled,and their member classes at depth
     * 0. Collections filled at depth 0.</li>
     * <li>Depth 2 member classes at depth 1. Collections at depth 1.</li>
     * <li>Depth 3 ...</li>
     * </ol>
     *
     * @param id an Object holding the identifier, typically String or Integer
     * @return retrieved object or null
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    public C0 retrieve(Object id) throws DAOException;

    /**
     * Updates the given entity in the database. No cascade.
     *
     * @param domainObject Domain model object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    public C0 update(C0 domainObject) throws DAOException;

    /**
     * Deletes the given entity in the database.
     *
     * @param domainObject Domain model object
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    public void delete(C0 domainObject) throws DAOException;

    /**
     * Returns a list with all the elements of the given class.
     *
     * @return List of domain objects
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    public List<C0> retrieveAll() throws DAOException;

    /**
     * Returns a list with all the elements of the given class that match the
     * given parameters.
     *
     * @param parameters Map of String and Object.
     * @return List of domain objects
     * @throws local.tin.tests.model.domain.exceptions.DAOException
     */
    public List<C0> retrieveAllByParameters(Map<String, Object> parameters) throws DAOException;

    
}
