package local.tin.examples.jetty.embedded.camelCaser.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author benitodarder
 */
public abstract class AbstractCamelCaserImpl implements ICamelCaser {

    public static final String UPPER_CAMEL_CASER_REGEX = ".*?( [a-z])?.*?";

    private static final Logger LOGGER = Logger.getLogger(AbstractCamelCaserImpl.class.getCanonicalName());


    public String transformToLowerCamelCase(String source) {
        StringBuffer stringBuffer = new StringBuffer();
        Matcher m = Pattern.compile(UPPER_CAMEL_CASER_REGEX).matcher(source);
        LOGGER.log(Level.SEVERE, "Found: {0} groups, for regex: " + UPPER_CAMEL_CASER_REGEX + ", applied to: {1}", new Object[]{m.groupCount(), source});
        while (m.find()) {
            m.appendReplacement(stringBuffer, m.group().toUpperCase());
        }
        m.appendTail(stringBuffer);
        return stringBuffer
                .toString()
                .replace(" ", "");
    }

}
