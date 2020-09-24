package local.tin.tests.dao.impl;

import local.tin.tests.model.domain.exceptions.DAOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author benitodarder
 */
public class AssemblyDAOTest extends BaseDAOTest {
    
    private AssemblyDAO dao;
    private local.tin.tests.model.domain.product.Assembly domainObject;
    private local.tin.tests.model.data.product.Assembly dataObject;
    private local.tin.tests.model.data.embeddables.AssemblyId dataObjectId;
    private local.tin.tests.model.domain.compositeIds.AssemblyId domainbjectId;
    private ProductDAO mockedProductDAO;
    private local.tin.tests.model.domain.product.Product mockedDomainProduct;
    private local.tin.tests.model.data.product.Product mockedDataProduct; 

    
    @Before
    public void setUp() throws DAOException {
        setUpBaseMocks();
        dao = new AssemblyDAO(mockedEntityManagerFactory);
        domainObject = new local.tin.tests.model.domain.product.Assembly();
        dataObject = new local.tin.tests.model.data.product.Assembly();
        dataObjectId = new local.tin.tests.model.data.embeddables.AssemblyId();
        domainbjectId = new local.tin.tests.model.domain.compositeIds.AssemblyId();
        mockedProductDAO = mock(ProductDAO.class);
        when(mockedDAOFactory.getDAO(local.tin.tests.model.data.product.Product.class)).thenReturn(mockedProductDAO);
        mockedDomainProduct = mock(local.tin.tests.model.domain.product.Product.class);
        mockedDataProduct = mock(local.tin.tests.model.data.product.Product.class);
        when(mockedProductDAO.getDataObject(eq(mockedDomainProduct), anyInt())).thenReturn(mockedDataProduct);
        when(mockedProductDAO.getDomainObject(eq(mockedDataProduct), anyInt())).thenReturn(mockedDomainProduct);
        setComponentMocks();
    }



    @Test
    public void updateDomainObjectDepth0Fields_assigns_fields() throws DAOException {
        dataObject.setQuantity(69d);
        
        dao.updateDomainObjectDepth0Fields(domainObject, dataObject);
        
        assertThat(domainObject.getQuantity(), equalTo(69d));
    }
    
    @Test
    public void updateDataObjectDepth0Fields_assigns_fields() throws DAOException {
        domainObject.setQuantity(69d);
        
        dao.updateDataObjectDepth0Fields(domainObject, dataObject);
        
        assertThat(dataObject.getQuantity(), equalTo(69d));
    }   
    
    @Test
    public void updateDomainObjectDeeperFields_assigns_fields() throws DAOException {
        dataObject.setComponent(mockedDataComponent);
        dataObject.setProduct(mockedDataProduct);
        
        dao.updateDomainObjectDeeperFields(domainObject, dataObject, 0);
        
        assertThat(domainObject.getComponent(), equalTo(mockedDomainComponent));
        assertThat(domainObject.getProduct(), equalTo(mockedDomainProduct));
    }
    
    @Test
    public void updateDatabjectDeeperFields_do_assigns_fields() throws DAOException {
        domainObject.setComponent(mockedDomainComponent);
        domainObject.setProduct(mockedDomainProduct);
        
        dao.updateDataObjectDeeperFields(domainObject, dataObject, 0);
        
        assertThat(dataObject.getComponent(), nullValue());
        assertThat(dataObject.getProduct(), nullValue());
    }    
    
    @Test
    public void getEmmbeddedId_assigns_fields() throws DAOException {
        domainbjectId.setComponentId(666);
        domainbjectId.setProductId(69);
        
        dataObjectId = dao.getEmmbeddedId(domainbjectId);
        
        assertThat(dataObjectId.getComponentId(), equalTo(666));
        assertThat(dataObjectId.getProductId(), equalTo(69));
    }
    
    @Test
    public void getCompositedId_assigns_fields() throws DAOException {
        dataObjectId.setComponentId(666);
        dataObjectId.setProductId(69);
        
        domainbjectId = dao.getCompositedId(dataObjectId);
        
        assertThat(domainbjectId.getComponentId(), equalTo(666));
        assertThat(domainbjectId.getProductId(), equalTo(69));
    }    
}
