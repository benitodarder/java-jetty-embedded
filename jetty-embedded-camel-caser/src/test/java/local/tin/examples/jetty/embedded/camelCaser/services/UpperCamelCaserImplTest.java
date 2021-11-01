package local.tin.examples.jetty.embedded.camelCaser.services;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author benitodarder
 */
public class UpperCamelCaserImplTest {
    
    private static final String SAMPLE_SOURCE_01 = "this is a Test";
    private static final String SAMPLE_OUTPUT_01 = "ThisIsATest";
    private UpperCamelCaserImpl service;
    
    @Before
    public void setUp() {
         service = new UpperCamelCaserImpl();
    }

    @Test
    public void transfer_returns_expected_result() {
        
        assertEquals(SAMPLE_OUTPUT_01, service.transform(SAMPLE_SOURCE_01));
    }
}
