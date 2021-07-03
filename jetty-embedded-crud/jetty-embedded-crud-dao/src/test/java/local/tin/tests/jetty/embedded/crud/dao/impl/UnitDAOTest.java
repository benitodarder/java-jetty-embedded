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
public class UnitDAOTest extends BaseDAOTest {

    protected static final String ABBREVIATION = "abbreviation";    
    private UnitDAO dao;
    private local.tin.tests.jetty.embedded.crud.models.domain.product.Unit domainObject;
    private local.tin.tests.jetty.embedded.crud.models.data.product.Unit dataObject;
    
    @Before
    public void setUp() throws DAOException {
        setUpBaseMocks();
        setComponentMocks();
        dao = new UnitDAO(mockedEntityManagerFactory);
        domainObject = new local.tin.tests.jetty.embedded.crud.models.domain.product.Unit();
        dataObject = new local.tin.tests.jetty.embedded.crud.models.data.product.Unit();
    }
            
    @Test
    public void updateDomainObjectDepth0Fields_assigns_fields() throws DAOException {
        dataObject.setAbbreviation(ABBREVIATION);
        dataObject.setEnabled(true);
        dataObject.setId(ID);
        dataObject.setName(NAME);
        
        dao.updateDomainObjectDepth0Fields(domainObject, dataObject);
        
        assertThat(domainObject.getAbbreviation(), equalTo(ABBREVIATION));
        assertThat(domainObject.getName(), equalTo(NAME));
    }
    
    @Test
    public void updateDataObjectDepth0Fields_assigns_fields() throws DAOException {
        domainObject.setAbbreviation(ABBREVIATION);
        domainObject.setEnabled(true);
        domainObject.setId(ID);
        domainObject.setName(NAME);
        
        dao.updateDataObjectDepth0Fields(domainObject, dataObject);
        
        assertThat(dataObject.getAbbreviation(), equalTo(ABBREVIATION));
        assertThat(dataObject.getName(), equalTo(NAME));
    }    
    
    @Test
    public void updateDomainObjectDeeperFields_assigns_fields() throws DAOException {
        dataObject.getComponents().add(mockedDataComponent);
        
        dao.updateDomainObjectDeeperFields(domainObject, dataObject, 0);
        
        assertThat(domainObject.getComponents().contains(mockedDomainComponent), equalTo(true));
    }

    @Test
    public void updateDataObjectDeeperFields_assigns_fields() throws DAOException {
        domainObject.getComponents().add(mockedDomainComponent);
        
        dao.updateDataObjectDeeperFields(domainObject, dataObject, 0);
        
        assertThat(dataObject.getComponents().contains(mockedDataComponent), equalTo(true));
    }
    
}
