package local.tin.examples.jetty.embedded.camelCaser.services;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author benitodarder
 */
public class LowerCamelCaserImplTest {

    private static final String SAMPLE_SOURCE_01 = "this is a Test";
    private static final String SAMPLE_OUTPUT_01 = "thisIsATest";
    private static final String ONE_CHAR_ORIGINAL = "a";
    private static final String ONE_CHAR_TRANSFORMED = "a";
    private static final String EMPTY_STRING = "";
    private LowerCamelCaserImpl service;

    @Before
    public void setUp() {
        service = new LowerCamelCaserImpl();
    }

    @Test
    public void transform_returns_expected_result() {

        assertEquals(SAMPLE_OUTPUT_01, service.transform(SAMPLE_SOURCE_01));
    }

    @Test
    public void transform_of_empty_string_is_empty_string() {

        assertEquals(EMPTY_STRING, service.transform(EMPTY_STRING));
    }

    @Test
    public void transform_of_one_char_string_is_one_char_string() {

        assertEquals(ONE_CHAR_TRANSFORMED, service.transform(ONE_CHAR_ORIGINAL));
    }

}
