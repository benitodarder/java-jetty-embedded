package local.tin.tests.utils.ftp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author benitodarder
 */
public class RegexUtils {
    
    private RegexUtils() {
    }
    
    public static RegexUtils getInstance() {
        return RegexUtilsHolder.INSTANCE;
    }
    
    private static class RegexUtilsHolder {

        private static final RegexUtils INSTANCE = new RegexUtils();
    }
    
    /**
     * Return the matching group for the given REGEX applied to the 
     * source String.
     * 
     * Return null when no match is found.
     * 
     * @param source String
     * @param regex String 
     * @param group int
     * @return String
     */
    public String getGroup(String source, String regex, int group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        if (!matcher.find()) {
            return null;
        }
        return matcher.group(group);
    }
    
    /**
     * Return the matching substring for the given REGEX applied to the 
     * source String.
     * 
     * Return null when no match is found.
     * 
     * @param source String
     * @param regex String 
     * @return String
     */
    public String getGroup(String source, String regex) {
        return getGroup(source, regex, 1);
    }    
}
