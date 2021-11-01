package local.tin.examples.jetty.embedded.camelCaser.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class UpperCamelCaserImpl implements ICamelCaser {

    public static final String UPPER_CAMEL_CASER_REGEX = ".*?( [a-z])?.*?";
    
    private static final Logger LOGGER = Logger.getLogger(UpperCamelCaserImpl.class);
    
    @Override
    public String transform(String source) {
        StringBuffer stringBuffer = new StringBuffer();
        Matcher m = Pattern.compile(UPPER_CAMEL_CASER_REGEX).matcher(source);
        LOGGER.debug("Found: " + m.groupCount() + " groups, for regex: " + UPPER_CAMEL_CASER_REGEX + ", applied to: " + source);
        while (m.find()) {
            m.appendReplacement(stringBuffer, m.group().toUpperCase());
        }
        m.appendTail(stringBuffer);
        String result = stringBuffer.toString();
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        result = result.replace(" ", "");
        return result;
    }

}
