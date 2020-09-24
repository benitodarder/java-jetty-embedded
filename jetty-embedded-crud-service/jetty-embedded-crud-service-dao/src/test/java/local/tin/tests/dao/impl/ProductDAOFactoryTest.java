package local.tin.tests.dao.impl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import local.tin.tests.dao.ProductDAOConfiguration;
import local.tin.tests.model.domain.exceptions.DAOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author benitodarder
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Persistence.class, ProductDAOConfiguration.class, EntityManagerFactory.class})
public class ProductDAOFactoryTest {
    
    @Test
    public void getInstance_initializes_entitymanagerfactory() throws DAOException {
        ProductDAOConfiguration mockedProductDAOConfiguration = mock(ProductDAOConfiguration.class);
        PowerMockito.mockStatic(ProductDAOConfiguration.class);
        when(ProductDAOConfiguration.getInstance()).thenReturn(mockedProductDAOConfiguration);
        when(mockedProductDAOConfiguration.getPersistenceUnit()).thenReturn("persistence unit");
        EntityManagerFactory mockedEntityManagerFactory = mock(EntityManagerFactory.class);
        PowerMockito.mockStatic(Persistence.class);
        when(Persistence.createEntityManagerFactory("persistence unit")).thenReturn(mockedEntityManagerFactory);
        
        ProductDAOFactory.getInstance();
        
        assertThat(ProductDAOFactory.getInstance().getEntityManagerFactory(), equalTo(mockedEntityManagerFactory));
    }
    
}
