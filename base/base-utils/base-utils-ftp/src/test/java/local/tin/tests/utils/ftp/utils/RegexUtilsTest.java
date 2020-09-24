package local.tin.tests.utils.ftp.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author benitodarder
 */
public class RegexUtilsTest {

    private static final String SAMPLE_REGEX_GROUPS = "(.*) (.*) (.*)";
    private static final String SAMPLE_REGEX_SINGLE_GROUP = ".* (.*) .*";
    private static final String GROUP_CENTER = "que";
    private static final String GROUP_BEGIN = "hola";
    private static final String GROUP_END = "tal";
    private static final String SAMPLE_STRING = GROUP_BEGIN + " " + GROUP_CENTER + " " + GROUP_END;
    private static final String NOT_MATCHING_STRING = "asdfg";

    @Test
    public void getGroup_returns_group_when_present() {

        String result = RegexUtils.getInstance().getGroup(SAMPLE_STRING, SAMPLE_REGEX_GROUPS, 3);

        assertThat(result, equalTo(GROUP_END));
    }

    @Test
    public void getGroup_returns_null_when_no_present() {

        String result = RegexUtils.getInstance().getGroup(NOT_MATCHING_STRING, SAMPLE_REGEX_GROUPS, 1);

        assertThat(result, nullValue());
    }

    @Test
    public void getGroup_returns_matching_substring_when_present() {

        String result = RegexUtils.getInstance().getGroup(SAMPLE_STRING, SAMPLE_REGEX_SINGLE_GROUP);

        assertThat(result, equalTo(GROUP_CENTER));
    }    
}
