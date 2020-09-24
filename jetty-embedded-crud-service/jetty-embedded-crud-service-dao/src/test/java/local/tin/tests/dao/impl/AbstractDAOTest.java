package local.tin.tests.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import local.tin.tests.model.data.interfaces.IIdentifiable;
import local.tin.tests.model.domain.exceptions.CommonException;
import local.tin.tests.model.domain.exceptions.DAOException;
import local.tin.tests.model.domain.interfaces.ICompositeId;
import org.apache.log4j.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;

/**
 *
 * @author benito.darder
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Logger.class, EntityManager.class, IllegalArgumentException.class, IIdentifiable.class})
public class AbstractDAOTest extends BaseDAOTest {

    public static final String QUERY_PREFIX = "select e from IIdentifiable e";
    public static final String QUERY_SUFIX_01 = " where e.p1 = :p1 and e.p2 = :p2";
    public static final String QUERY_SUFIX_02 = " where e.p2 = :p2 and e.p1 = :p1";
    protected static local.tin.tests.model.data.interfaces.IIdentifiable mockedAbstractDataObject;
    protected static local.tin.tests.model.domain.interfaces.IIdentifiable mockedAbstractDomainObject;
    protected static local.tin.tests.model.domain.interfaces.ICompositeId mockedICompositeId;
    protected static local.tin.tests.model.data.interfaces.IEmbeddable mockedEmbeddable;
    private static final String LOCALIZED_MESSAGE = "Fai un sol de caralho";
    private static Logger mockedLogger;
    private EntityTransaction mockedEntityTransaction;
    private AbstractDAO dao;

    @BeforeClass
    public static void setUpClass() {
        BaseDAOTest.setUpClass();
        mockedLogger = mock(Logger.class);
        PowerMockito.mockStatic(Logger.class);
        when(Logger.getLogger(AbstractDAO.class)).thenReturn(mockedLogger);
        mockedAbstractDataObject = mock(IIdentifiable.class);
        mockedAbstractDomainObject = mock(local.tin.tests.model.domain.interfaces.IIdentifiable.class);
        mockedICompositeId = mock(local.tin.tests.model.domain.interfaces.ICompositeId.class);
        mockedEmbeddable = mock(local.tin.tests.model.data.interfaces.IEmbeddable.class);
    }

    @Before
    public void setUp() {
        setUpBaseMocks();
        mockedEntityTransaction = mock(EntityTransaction.class);
        when(mockedEntityManager.getTransaction()).thenReturn(mockedEntityTransaction);
        dao = new AbstractDAOWrapperWithEmbeddedId(mockedEntityManagerFactory);
        reset(mockedLogger);

    }

    @Test
    public void persist_does_not_close_entity_manager() throws DAOException {

        dao.persist(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityManager, never()).close();
    }

    @Test
    public void persist_begins_transaction() throws DAOException {

        dao.persist(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).begin();
    }

    @Test
    public void persist_the_parameter() throws DAOException {

        dao.persist(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityManager).persist(mockedAbstractDataObject);
    }

    @Test
    public void persist_commits_transaction() throws DAOException {

        dao.persist(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).commit();
    }

    @Test(expected = DAOException.class)
    public void persist_rollsback_transaction_if_an_exception_is_thrown() throws DAOException {
        PowerMockito.doThrow(new EntityExistsException(LOCALIZED_MESSAGE)).when(mockedEntityManager).persist(mockedAbstractDataObject);

        dao.persist(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).rollback();
    }

    @Test(expected = DAOException.class)
    public void persist_logs_exception() throws DAOException {
        TransactionRequiredException mockedRuntimeException = mock(TransactionRequiredException.class);
        when(mockedRuntimeException.getLocalizedMessage()).thenReturn(LOCALIZED_MESSAGE);
        PowerMockito.doThrow(mockedRuntimeException).when(mockedEntityManager).persist(mockedAbstractDataObject);

        dao.persist(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedLogger).error(dao.getExceptionMessage(mockedRuntimeException));
    }

    @Test
    public void merge_does_not_close_entity_manager() throws DAOException {

        dao.merge(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityManager, never()).close();
    }

    @Test
    public void merge_begins_transaction() throws DAOException {

        dao.merge(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).begin();
    }

    @Test
    public void merge_the_parameter() throws DAOException {

        dao.merge(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityManager).merge(mockedAbstractDataObject);
    }

    @Test
    public void merge_commits_transaction() throws DAOException {

        dao.merge(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).commit();
    }

    @Test(expected = DAOException.class)
    public void merge_rollsback_transaction_if_an_exception_is_thrown() throws DAOException {
        PowerMockito.doThrow(new TransactionRequiredException(LOCALIZED_MESSAGE)).when(mockedEntityManager).merge(mockedAbstractDataObject);

        dao.merge(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).rollback();
    }

    @Test(expected = DAOException.class)
    public void merge_logs_exception() throws DAOException {
        TransactionRequiredException mockedRuntimeException = mock(TransactionRequiredException.class);
        when(mockedRuntimeException.getLocalizedMessage()).thenReturn(LOCALIZED_MESSAGE);
        PowerMockito.doThrow(mockedRuntimeException).when(mockedEntityManager).merge(mockedAbstractDataObject);

        dao.merge(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedLogger).error(dao.getExceptionMessage(mockedRuntimeException));
    }

    @Test
    public void merge_returns_the_merged_object() throws DAOException {
        IIdentifiable anotherMockedAxisDataObject = mock(IIdentifiable.class);
        when(mockedEntityManager.merge(mockedAbstractDataObject)).thenReturn(anotherMockedAxisDataObject);

        IIdentifiable result = dao.merge(mockedEntityManager, mockedAbstractDataObject);

        assertThat(result, equalTo(anotherMockedAxisDataObject));
    }

    @Test
    public void refresh_does_not_close_entity_manager() throws DAOException {

        dao.refresh(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityManager, never()).close();
    }

    @Test
    public void refresh_begins_transaction() throws DAOException {

        dao.refresh(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).begin();
    }

    @Test
    public void refresh_the_parameter() throws DAOException {

        dao.refresh(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityManager).refresh(mockedAbstractDataObject);
    }

    @Test
    public void refresh_commits_transaction() throws DAOException {

        dao.refresh(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).commit();
    }

    @Test(expected = DAOException.class)
    public void refresh_rollsback_transaction_if_an_exception_is_thrown() throws DAOException {
        PowerMockito.doThrow(new EntityExistsException(LOCALIZED_MESSAGE)).when(mockedEntityManager).refresh(mockedAbstractDataObject);

        dao.refresh(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).rollback();
    }

    @Test(expected = DAOException.class)
    public void refresh_logs_exception() throws DAOException {
        TransactionRequiredException mockedRuntimeException = mock(TransactionRequiredException.class);
        when(mockedRuntimeException.getLocalizedMessage()).thenReturn(LOCALIZED_MESSAGE);
        PowerMockito.doThrow(mockedRuntimeException).when(mockedEntityManager).refresh(mockedAbstractDataObject);

        dao.refresh(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedLogger).error(dao.getExceptionMessage(mockedRuntimeException));
    }

    @Test
    public void remove_does_not_close() throws DAOException {

        dao.remove(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityManager, never()).close();
    }

    @Test
    public void remove_begins_transaction() throws DAOException {

        dao.remove(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).begin();
    }

    @Test
    public void remove_the_parameter() throws DAOException {
        when(mockedEntityManager.merge(mockedAbstractDataObject)).thenReturn(mockedAbstractDataObject);

        dao.remove(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityManager).remove(mockedAbstractDataObject);
    }

    @Test
    public void remove_commits_transaction() throws DAOException {

        dao.remove(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).commit();
    }

    @Test(expected = DAOException.class)
    public void remove_rollsback_transaction_if_an_exception_is_thrown() throws DAOException {
        PowerMockito.doThrow(new TransactionRequiredException(LOCALIZED_MESSAGE)).when(mockedEntityManager).remove(any());

        dao.remove(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityTransaction).rollback();
    }

    @Test(expected = DAOException.class)
    public void remove_logs_exception() throws DAOException {
        TransactionRequiredException mockedRuntimeException = mock(TransactionRequiredException.class);
        when(mockedRuntimeException.getLocalizedMessage()).thenReturn(LOCALIZED_MESSAGE);
        PowerMockito.doThrow(mockedRuntimeException).when(mockedEntityManager).remove(any());

        dao.remove(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedLogger).error(dao.getExceptionMessage(mockedRuntimeException));
    }

    @Test
    public void find_does_not_close_entity_manager() throws DAOException {

        dao.findById(mockedEntityManager, mockedAbstractDataObject);

        verify(mockedEntityManager, never()).close();
    }

    @Test
    public void find_the_parameter() throws DAOException {

        dao.findById(mockedEntityManager, ID);

        verify(mockedEntityManager).find(dao.getDAOClass(), ID);
    }

    @Test(expected = DAOException.class)
    public void find_logs_exception() throws DAOException {
        IllegalArgumentException mockedRuntimeException = mock(IllegalArgumentException.class);
        when(mockedRuntimeException.getLocalizedMessage()).thenReturn(LOCALIZED_MESSAGE);
        PowerMockito.doThrow(mockedRuntimeException).when(mockedEntityManager).find(dao.getDAOClass(), ID);

        dao.findById(mockedEntityManager, ID);

        verify(mockedLogger).error(dao.getExceptionMessage(mockedRuntimeException));
    }

    @Test
    public void find_returns_the_object_retrieved() throws DAOException {
        IIdentifiable anotherMockedAxisDataObject = mock(IIdentifiable.class);
        when(mockedEntityManager.find(dao.getDAOClass(), ID)).thenReturn(anotherMockedAxisDataObject);

        IIdentifiable result = dao.findById(mockedEntityManager, ID);

        assertThat(result, equalTo(anotherMockedAxisDataObject));
    }

    @Test
    public void getEntityManager_return_the_expected_object() {

        EntityManager entityManager = dao.getEntityManager();

        assertThat(entityManager, equalTo(mockedEntityManager));
    }

    private Map<String, Object> getParametersMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("p1", "o1");
        parameters.put("p2", 4);
        return parameters;
    }

    @Test
    public void findByParameters_creates_expected_query_string_with_parameters() throws DAOException {
        Map<String, Object> parameters = getParametersMap();
        Query mockedTypedQuery = mock(Query.class);
        when(mockedEntityManager.createQuery(QUERY_PREFIX + QUERY_SUFIX_01)).thenReturn(mockedTypedQuery);
        when(mockedEntityManager.createQuery(QUERY_PREFIX + QUERY_SUFIX_02)).thenReturn(mockedTypedQuery);
        when(mockedTypedQuery.setParameter("p1", "o1")).thenReturn(mockedTypedQuery);
        when(mockedTypedQuery.setParameter("p2", 4)).thenReturn(mockedTypedQuery);

        dao.findByParameters(mockedEntityManager, parameters);

        verify(mockedEntityManager).createQuery(anyString());
    }

    @Test
    public void findByParameters_creates_expected_when_parameters_is_null() throws DAOException {
        Query mockedTypedQuery = mock(Query.class);
        when(mockedEntityManager.createQuery(QUERY_PREFIX)).thenReturn(mockedTypedQuery);

        dao.findByParameters(mockedEntityManager, null);

        verify(mockedEntityManager).createQuery(QUERY_PREFIX);
    }

    @Test
    public void findByParameters_creates_expected_when_parameters_is_empty() throws DAOException {
        Query mockedTypedQuery = mock(Query.class);
        when(mockedEntityManager.createQuery(QUERY_PREFIX)).thenReturn(mockedTypedQuery);

        dao.findByParameters(mockedEntityManager, new HashMap<String, Object>());

        verify(mockedEntityManager).createQuery(QUERY_PREFIX);
    }

    @Test
    public void findByParameters_returns_expected_result_list() throws DAOException {
        Map<String, Object> parameters = getParametersMap();
        Query mockedTypedQuery = mock(Query.class);
        when(mockedEntityManager.createQuery(QUERY_PREFIX + QUERY_SUFIX_01)).thenReturn(mockedTypedQuery);
        when(mockedEntityManager.createQuery(QUERY_PREFIX + QUERY_SUFIX_02)).thenReturn(mockedTypedQuery);
        when(mockedTypedQuery.setParameter("p1", "o1")).thenReturn(mockedTypedQuery);
        when(mockedTypedQuery.setParameter("p2", 4)).thenReturn(mockedTypedQuery);
        List<IIdentifiable> result = new ArrayList<>();
        when(mockedTypedQuery.getResultList()).thenReturn(result);

        List<IIdentifiable> list = dao.findByParameters(mockedEntityManager, parameters);

        assertThat(list, equalTo(result));
    }

    @Test
    public void updateDataObjectId_assings_atomic_id() throws DAOException {
        when(mockedAbstractDomainObject.getId()).thenReturn(ID);

        dao.updateDataObjectId(mockedAbstractDataObject, mockedAbstractDomainObject);

        verify(mockedAbstractDataObject).setId(ID);
    }

    @Test
    public void updateDataObjectId_assings_embedded_id() throws DAOException {
        when(mockedAbstractDomainObject.getId()).thenReturn(mockedICompositeId);

        dao.updateDataObjectId(mockedAbstractDataObject, mockedAbstractDomainObject);

        verify(mockedAbstractDataObject).setId(mockedEmbeddable);
    }

    @Test
    public void updateDomainObjectId_assings_atomic_id() throws DAOException {
        when(mockedAbstractDataObject.getId()).thenReturn(ID);

        dao.updateDomainObjectId(mockedAbstractDataObject, mockedAbstractDomainObject);

        verify(mockedAbstractDataObject).setId(ID);
    }

    @Test
    public void updateDomainObjectId_assings_embedded_id() throws DAOException {
        when(mockedAbstractDataObject.getId()).thenReturn(mockedEmbeddable);

        dao.updateDomainObjectId(mockedAbstractDataObject, mockedAbstractDomainObject);

        verify(mockedAbstractDomainObject).setId(mockedICompositeId);
    }

    @Test
    public void getLogger_returns_class_logger() {

        Logger result = dao.getLogger();

        assertThat(result, equalTo(mockedLogger));
    }

    @Test
    public void updateDomainCommonFields_assigns_fields() throws DAOException {
        when(mockedAbstractDataObject.isEnabled()).thenReturn(true);

        dao.updateDomainCommonFields(mockedAbstractDomainObject, mockedAbstractDataObject);

        verify(mockedAbstractDomainObject).setEnabled(true);
    }

    @Test
    public void updateDataCommonFields_assigns_fields() throws DAOException {
        when(mockedAbstractDomainObject.isEnabled()).thenReturn(true);

        dao.updateDataCommonFields(mockedAbstractDomainObject, mockedAbstractDataObject);

        verify(mockedAbstractDataObject).setEnabled(true);
    }

    @Test
    public void getDomainObject_returns_expected_object() throws DAOException {

        local.tin.tests.model.domain.interfaces.IIdentifiable result = dao.getDomainObject(mockedAbstractDataObject, 0);

        assertThat(result, equalTo(mockedAbstractDomainObject));
    }

    @Test
    public void getDataObject_returns_expected_object() throws DAOException {

        local.tin.tests.model.data.interfaces.IIdentifiable result = dao.getDataObject(mockedAbstractDomainObject, 0);

        assertThat(result, equalTo(mockedAbstractDataObject));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getEmbededId_throws_unsupported_operation_exception_when_not_defined() throws DAOException {
        dao = new AbstractDAOWrapper(mockedEntityManagerFactory);

        dao.getEmmbeddedId(mockedICompositeId);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getCompositeId_throws_unsupported_operation_exception_when_not_defined() throws DAOException {
        dao = new AbstractDAOWrapper(mockedEntityManagerFactory);

        dao.getCompositedId(mockedEmbeddable);
    }

    @Test
    public void find_the_parameter_when_composite() throws DAOException {

        dao.findById(mockedEntityManager, mockedICompositeId);

        verify(mockedEntityManager).find(dao.getDAOClass(), mockedEmbeddable);
    }

    @Test
    public void create_assigns_id() throws DAOException {
        reset(mockedAbstractDomainObject);
        when(mockedAbstractDataObject.getId()).thenReturn(mockedEmbeddable);
        
        local.tin.tests.model.domain.interfaces.IIdentifiable result = dao.create(mockedAbstractDomainObject);

        verify(mockedAbstractDomainObject).setId(mockedICompositeId);
    }

    @Test
    public void retrieve_returns_object() throws DAOException {
        when(mockedEntityManager.find(IIdentifiable.class, mockedEmbeddable)).thenReturn(mockedAbstractDataObject);

        local.tin.tests.model.domain.interfaces.IIdentifiable result = dao.retrieve(mockedICompositeId);

        assertThat(result, equalTo(mockedAbstractDomainObject));
    }

//A 

    @Test
    public void delete_removes_object() throws DAOException {
        when(mockedAbstractDomainObject.getId()).thenReturn(mockedICompositeId);
        when(mockedEntityManager.find(IIdentifiable.class, mockedEmbeddable)).thenReturn(mockedAbstractDataObject);
        when(mockedEntityManager.merge(mockedAbstractDataObject)).thenReturn(mockedAbstractDataObject);
        
        dao.delete(mockedAbstractDomainObject);

        verify(mockedEntityManager).remove(mockedAbstractDataObject);
    }
    
    @Test
    public void retrieveAll_returns_expected_query() throws DAOException {
        Query mockedTypedQuery = mock(Query.class);
        when(mockedEntityManager.createQuery(QUERY_PREFIX)).thenReturn(mockedTypedQuery);
        List<local.tin.tests.model.data.interfaces.IIdentifiable> dataResults = new ArrayList<>();
        dataResults.add(mockedAbstractDataObject);
        when(mockedTypedQuery.getResultList()).thenReturn(dataResults);
        
        List<local.tin.tests.model.domain.interfaces.IIdentifiable> results = dao.retrieveAll();

        assertThat(results.size(), equalTo(1));
        assertThat(results.get(0), equalTo(mockedAbstractDomainObject));
    }    
    
    @Test
    public void update_merges_object() throws DAOException {
        reset(mockedEntityManager);
        when(mockedEntityManager.getTransaction()).thenReturn(mockedEntityTransaction);
        when(mockedEntityManager.merge(mockedAbstractDataObject)).thenReturn(mockedAbstractDataObject);
        
        local.tin.tests.model.domain.interfaces.IIdentifiable result = dao.update(mockedAbstractDomainObject);

        verify(mockedEntityManager).merge(mockedAbstractDataObject);
    }    
}

class AbstractDAOWrapperWithEmbeddedId extends AbstractDAO<local.tin.tests.model.domain.interfaces.IIdentifiable, local.tin.tests.model.data.interfaces.IIdentifiable> {

    public AbstractDAOWrapperWithEmbeddedId(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);

    }

    @Override
    protected Class<IIdentifiable> getDAOClass() {
        return IIdentifiable.class;
    }

    @Override
    protected local.tin.tests.model.data.interfaces.IEmbeddable getEmmbeddedId(ICompositeId compositeId) throws DAOException {
        return AbstractDAOTest.mockedEmbeddable;
    }

    @Override
    protected ICompositeId getCompositedId(local.tin.tests.model.data.interfaces.IEmbeddable iEmbeddable) throws DAOException {
        return AbstractDAOTest.mockedICompositeId;
    }

    @Override
    protected local.tin.tests.model.domain.interfaces.IIdentifiable updateDomainObjectDepth0Fields(local.tin.tests.model.domain.interfaces.IIdentifiable domainObject, IIdentifiable dataObject) throws DAOException {
        return AbstractDAOTest.mockedAbstractDomainObject;
    }

    @Override
    protected local.tin.tests.model.domain.interfaces.IIdentifiable updateDomainObjectDeeperFields(local.tin.tests.model.domain.interfaces.IIdentifiable domainObject, IIdentifiable dataObject, int depth) throws DAOException {
        return AbstractDAOTest.mockedAbstractDomainObject;
    }

    @Override
    protected IIdentifiable updateDataObjectDepth0Fields(local.tin.tests.model.domain.interfaces.IIdentifiable domainObject, IIdentifiable dataObject) throws DAOException {
        return AbstractDAOTest.mockedAbstractDataObject;
    }

    @Override
    protected IIdentifiable updateDataObjectDeeperFields(local.tin.tests.model.domain.interfaces.IIdentifiable domainObject, IIdentifiable dataObject, int depth) throws DAOException {
        return AbstractDAOTest.mockedAbstractDataObject;
    }

    @Override
    protected local.tin.tests.model.domain.interfaces.IIdentifiable getDomainObjectNewInstance() {
        return AbstractDAOTest.mockedAbstractDomainObject;
    }

    @Override
    protected IIdentifiable getDataObjectNewInstance() {
        return AbstractDAOTest.mockedAbstractDataObject;
    }

}

class AbstractDAOWrapper extends AbstractDAO<local.tin.tests.model.domain.interfaces.IIdentifiable, local.tin.tests.model.data.interfaces.IIdentifiable> {

    public AbstractDAOWrapper(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);

    }

    @Override
    protected Class<IIdentifiable> getDAOClass() {
        return IIdentifiable.class;
    }

    @Override
    protected local.tin.tests.model.domain.interfaces.IIdentifiable updateDomainObjectDepth0Fields(local.tin.tests.model.domain.interfaces.IIdentifiable domainObject, IIdentifiable dataObject) throws DAOException {
        return AbstractDAOTest.mockedAbstractDomainObject;
    }

    @Override
    protected local.tin.tests.model.domain.interfaces.IIdentifiable updateDomainObjectDeeperFields(local.tin.tests.model.domain.interfaces.IIdentifiable domainObject, IIdentifiable dataObject, int depth) throws DAOException {
        return AbstractDAOTest.mockedAbstractDomainObject;
    }

    @Override
    protected IIdentifiable updateDataObjectDepth0Fields(local.tin.tests.model.domain.interfaces.IIdentifiable domainObject, IIdentifiable dataObject) throws DAOException {
        return AbstractDAOTest.mockedAbstractDataObject;
    }

    @Override
    protected IIdentifiable updateDataObjectDeeperFields(local.tin.tests.model.domain.interfaces.IIdentifiable domainObject, IIdentifiable dataObject, int depth) throws DAOException {
        return AbstractDAOTest.mockedAbstractDataObject;
    }

    @Override
    protected local.tin.tests.model.domain.interfaces.IIdentifiable getDomainObjectNewInstance() {
        return AbstractDAOTest.mockedAbstractDomainObject;
    }

    @Override
    protected IIdentifiable getDataObjectNewInstance() {
        return AbstractDAOTest.mockedAbstractDataObject;
    }

}
