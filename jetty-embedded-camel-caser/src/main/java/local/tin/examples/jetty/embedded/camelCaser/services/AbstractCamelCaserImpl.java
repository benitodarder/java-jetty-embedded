package local.tin.examples.jetty.embedded.camelCaser.services;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author benitodarder
 */
public abstract class AbstractCamelCaserImpl implements ICamelCaser {

    public static final String UPPER_CAMEL_CASER_REGEX = ".*?( [a-z])?.*?";

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCamelCaserImpl.class.getCanonicalName());


    public String transformToLowerCamelCase(String source) {
        StringBuffer stringBuffer = new StringBuffer();
        Matcher m = Pattern.compile(UPPER_CAMEL_CASER_REGEX).matcher(source);
        LOGGER.error("Found: {0} groups, for regex: " + UPPER_CAMEL_CASER_REGEX + ", applied to: {1}", new Object[]{m.groupCount(), source});
        while (m.find()) {
            m.appendReplacement(stringBuffer, m.group().toUpperCase());
        }
        m.appendTail(stringBuffer);
        return stringBuffer
                .toString()
                .replace(" ", "");
    }

}
