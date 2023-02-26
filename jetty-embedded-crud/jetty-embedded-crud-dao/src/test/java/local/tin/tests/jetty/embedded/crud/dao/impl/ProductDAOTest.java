package local.tin.tests.jetty.embedded.crud.dao.impl;

import local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author benitodarder
 */
public class ProductDAOTest extends BaseDAOTest {

    private ProductDAO dao;
    private local.tin.tests.jetty.embedded.crud.models.domain.product.Product domainObject;
    private local.tin.tests.jetty.embedded.crud.dao.model.product.Product dataObject;

    
    @Before
    public void setUp() throws DAOException {    
        setUpBaseMocks();
        setAssemblyMocks();        
        dao = new ProductDAO(mockedEntityManagerFactory);
        domainObject = new local.tin.tests.jetty.embedded.crud.models.domain.product.Product();
        dataObject = new local.tin.tests.jetty.embedded.crud.dao.model.product.Product();
    }
            
    @Test
    public void updateDomainObjectDepth0Fields_assigns_fields() throws DAOException {
        dataObject.setDescription("description");
        dataObject.setName(NAME);
        
        dao.updateDomainObjectDepth0Fields(domainObject, dataObject);
        
        assertThat(domainObject.getName(), equalTo(NAME));
        assertThat(domainObject.getDescription(), equalTo("description"));
    }
    
    @Test
    public void updateDataObjectDepth0Fields_assigns_fields() throws DAOException {
        domainObject.setDescription("description");
        domainObject.setName(NAME);
        
        dao.updateDataObjectDepth0Fields(domainObject, dataObject);
        
        assertThat(dataObject.getName(), equalTo(NAME));
        assertThat(dataObject.getDescription(), equalTo("description"));        
    }    

    @Test
    public void updateDomainObjectDeeperFields_assigns_fields() throws DAOException {
        dataObject.getAssemblies().add(mockedDataAssembly);
        
        dao.updateDomainObjectDeeperFields(domainObject, dataObject, 0);
        
        assertThat(domainObject.getAssemblies().contains(mockedDomainAssembly), equalTo(true));
    }
    
    @Test
    public void updateDataObjectDeeperFields_assigns_fields() throws DAOException {
        domainObject.getAssemblies().add(mockedDomainAssembly);
        
        dao.updateDataObjectDeeperFields(domainObject, dataObject, 0);
        
        assertThat(dataObject.getAssemblies().contains(mockedDataAssembly), equalTo(true));
    }    
}
