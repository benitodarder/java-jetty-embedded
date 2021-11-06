package local.tin.examples.jetty.embedded.camelCaser.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author benitodarder
 */
public class AbstractCamelCaserImplTest {

    private static final String SAMPLE_SOURCE_01 = "this is a Test";
    private static final String SAMPLE_OUTPUT_01 = "thisIsATest";
    private static final String ONE_CHAR_ORIGINAL = "a";
    private static final String ONE_CHAR_TRANSFORMED = "a";
    private static final String EMPTY_STRING = "";
    private AbstractCamelCaserImpl service;

    @Before
    public void setUp() {
        service = new AbstractCamelCaserImplWrapper();
    }

    @Test
    public void transformToLowerCamelCase_returns_expected_result() {

        assertEquals(SAMPLE_OUTPUT_01, service.transformToLowerCamelCase(SAMPLE_SOURCE_01));
    }

        @Test
    public void transformToLowerCamelCase_of_empty_string_is_empty_string() {

        assertEquals(EMPTY_STRING, service.transformToLowerCamelCase(EMPTY_STRING));
    }

    @Test
    public void transformToLowerCamelCase_of_one_char_string_is_one_char_string() {

        assertEquals(ONE_CHAR_TRANSFORMED, service.transformToLowerCamelCase(ONE_CHAR_ORIGINAL));
    }

    @Test
    public void transformToLowerCamelCase_result_does_not_contain_spaces() {

        assertFalse(service.transformToLowerCamelCase(SAMPLE_SOURCE_01).contains(" "));
    }

}

class AbstractCamelCaserImplWrapper extends AbstractCamelCaserImpl {

    @Override
    public String transform(String source) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}