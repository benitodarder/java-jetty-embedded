package local.tin.tests.jetty.embedded.core.dao.impl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import local.tin.tests.jetty.embedded.core.dao.AbstractDAOConfiguration;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author benito.darder
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Persistence.class, EntityManagerFactory.class, AbstractDAOConfiguration.class})
public class AbstractDAOFactoryTest {
    
    private static EntityManagerFactory mockedEntityManagerFactory;
    private AbstractDAOFactory dAOBaseFactory;
    
    @BeforeClass
    public static void setUpClass() {
        mockedEntityManagerFactory = mock(EntityManagerFactory.class);
        PowerMockito.mockStatic(Persistence.class);
        when(Persistence.createEntityManagerFactory(anyString())).thenReturn(mockedEntityManagerFactory);
    }
    
    
    @Before
    public void setUp() {
        dAOBaseFactory = new BaseFactoryWrapper();
    }
    
    @Test
    public void fake() {
        
    }
    
}

class BaseFactoryWrapper extends AbstractDAOFactory {

    @Override
    protected EntityManagerFactory getEntityManagerFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getDAOBasePackage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Logger getLogger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

