package local.tin.tests.common.base.configuration;

import local.tin.tests.common.base.configuration.AbstractConfiguration;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.powermock.reflect.Whitebox;

/**
 *
 * @author benitodarder
 */
public class AbstractConfigurationTest {
    
    protected Map<String, String> configurationMap;
    private AbstractConfiguration configuration;
    
    
    @Before
    public void setUp() {
        configurationMap = mock(Map.class);
        configuration = new AbstractConfigurationWrapper();
        Whitebox.setInternalState(configuration, "configurationMap", configurationMap);
    }
    
    @Test
    public void resetConfiguration_clears_map() {
        
        configuration.resetConfiguration();
        
        verify(configurationMap).clear();
    }
   
    @Test
    public void loadProperties_puts_properties_in_map() {
        String key = "key";
        String vale = "value";
        Properties properties = new Properties();
        properties.put(key, vale);
        
        configuration.loadProperties(properties);
        
        verify(configurationMap).put(key, vale);
    }
}

class AbstractConfigurationWrapper extends AbstractConfiguration {
    
    private Map<String, String> configurationMap;
    
    
    @Override
    public Map<String, String> getConfigurationMap() {
        return configurationMap;
    }

    @Override
    public Logger getLogger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}