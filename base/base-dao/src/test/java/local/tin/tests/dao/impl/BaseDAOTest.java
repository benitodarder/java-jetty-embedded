package local.tin.tests.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import local.tin.tests.model.domain.exceptions.DAOException;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author benitodarder
 */
public abstract class BaseDAOTest {


    protected static final int ID = 666;
    protected static final String NAME = "name";
    protected static EntityManagerFactory mockedEntityManagerFactory;
    protected EntityManager mockedEntityManager;
    
    @BeforeClass
    public static void setUpClass() {
        mockedEntityManagerFactory = mock(EntityManagerFactory.class);        
    }
        
    
    protected void setUpBaseMocks() {
        mockedEntityManager = mock(EntityManager.class);
        when(mockedEntityManagerFactory.createEntityManager()).thenReturn(mockedEntityManager);           
    }
      
}
