package local.tin.tests.utils.ftp.utils;

import local.tin.tests.utils.ftp.model.FTPDetails;
import local.tin.tests.utils.ftp.model.FTPProtocol;

/**
 *
 * @author benitodarder
 */
public class FTPUtils {
    
    public static final String REGEX_PROTOCOL = "(.*):\\/\\/";
    public static final String REGEX_USERNAME = ".*\\/\\/(.*):.*@.*";
    public static final String REGEX_PASWORD = ".*:(.*)@.*";
    public static final String REGEX_HOST = ".*@(.*):.*";
    public static final String REGEX_PORT = ".*:(.*)\\/.*";
    public static final String REGEX_PATH = ".*\\/(.*)$";
    
    private FTPUtils() {
    }
    
    public static FTPUtils getInstance() {
        return FTPUtilsHolder.INSTANCE;
    }
    
    private static class FTPUtilsHolder {

        private static final FTPUtils INSTANCE = new FTPUtils();
    }
    
    public FTPDetails getFTPDetails(String ftpURL) {
        FTPDetails ftpDetails = new FTPDetails();
        ftpDetails.setProtocol(FTPProtocol.valueOf(RegexUtils.getInstance().getGroup(ftpURL, REGEX_PROTOCOL).toUpperCase()));
        ftpDetails.setUsername(RegexUtils.getInstance().getGroup(ftpURL, REGEX_USERNAME));
        ftpDetails.setPassword(RegexUtils.getInstance().getGroup(ftpURL, REGEX_PASWORD));
        ftpDetails.setServer(RegexUtils.getInstance().getGroup(ftpURL, REGEX_HOST));
        ftpDetails.setPort(Integer.parseInt(RegexUtils.getInstance().getGroup(ftpURL, REGEX_PORT)));
        ftpDetails.setDestinationFolder(RegexUtils.getInstance().getGroup(ftpURL, REGEX_PATH));
        return ftpDetails;
    }
}
