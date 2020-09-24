package local.tin.tests.common.base.configuration;

import local.tin.tests.common.base.configuration.AbstractDAOConfiguration;
import java.util.HashMap;
import java.util.Map;
import local.tin.tests.model.domain.exceptions.DAOException;
import local.tin.tests.model.domain.exceptions.ServiceException;
import org.apache.log4j.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author benito.darder
 */
public class AbstractDAOConfigurationTest {

    private static final String SAMPLE_BOOLEAN = "true";    
    private static final String SAMPLE_CLASS_D = "D";
    private static final String SAMPLE_CLASS_C = "C";
    private static final String SAMPLE_CLASS_B = "B";
    private static final String SAMPLE_CLASS_A = "A";    
    private AbstractDAOConfiguration dAOConfiguration;
    
    @Before
    public void setUp() {
        dAOConfiguration = new DAOConfigurationWrapper();
    }
    
    
    @Test
    public void getDAOBasePackage_returns_value_assigned_by_setDAOBasePackage() throws DAOException {
        dAOConfiguration.setDAOBasePackage(AbstractDAOConfiguration.DAO_BASE_PACKAGE);

        String result = dAOConfiguration.getDAOBasePackage();

        assertThat(result, equalTo(AbstractDAOConfiguration.DAO_BASE_PACKAGE));
    }

    @Test
    public void getPersistenceUnit_returns_value_assigned_by_setPersistenceUnit() throws DAOException {
        dAOConfiguration.setPersistenceUnit(AbstractDAOConfiguration.DAO_PERSISTENCE_UNIT);

        String result = dAOConfiguration.getPersistenceUnit();

        assertThat(result, equalTo(AbstractDAOConfiguration.DAO_PERSISTENCE_UNIT));
    }  
    
    @Test(expected = DAOException.class)
    public void getDAOBasePackage_throws_DAOEXception_when_not_configured() throws DAOException {
        dAOConfiguration.resetConfiguration();

        String result = dAOConfiguration.getDAOBasePackage();

    }    
    
    @Test(expected = DAOException.class)
    public void getPersistenceUnit_throws_DAOEXception_when_not_configured() throws DAOException {
        dAOConfiguration.resetConfiguration();

        String result = dAOConfiguration.getPersistenceUnit();

    }       
    
    @Test(expected = DAOException.class)
    public void getDAOBasePackage_throws_DAOEXception_when_empty() throws DAOException {
        dAOConfiguration.resetConfiguration();

        String result = dAOConfiguration.getDAOBasePackage();

    }

    @Test(expected = DAOException.class)
    public void getPersistenceUnit_throws_DAOEXception_when_empty() throws DAOException {
        dAOConfiguration.resetConfiguration();

        String result = dAOConfiguration.getPersistenceUnit();

    }     
    
    @Test
    public void getCRUDBasePackage_returns_value_assigned_by_setCRUDBasePackage() throws ServiceException {
        dAOConfiguration.setCRUDBasePackage(AbstractDAOConfiguration.CRUD_BASE_PACKAGE);

        String result = dAOConfiguration.getCRUDBasePackage();

        assertThat(result, equalTo(AbstractDAOConfiguration.CRUD_BASE_PACKAGE));
    }
    
    @Test(expected = ServiceException.class)
    public void getCRUDBasePackage_throws_ServiceException_when_empty() throws ServiceException {
        dAOConfiguration.resetConfiguration();

        String result = dAOConfiguration.getCRUDBasePackage();

    }   
    
 
}

class DAOConfigurationWrapper extends AbstractDAOConfiguration {

    Map<String, String> map = new HashMap<>();

    @Override
    public Map<String, String> getConfigurationMap() {
        return map;
    }

    @Override
    public Logger getLogger() {
        return Logger.getRootLogger();
    }
}