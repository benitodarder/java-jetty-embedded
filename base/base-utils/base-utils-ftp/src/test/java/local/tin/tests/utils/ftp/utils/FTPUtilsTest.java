package local.tin.tests.utils.ftp.utils;

import local.tin.tests.utils.ftp.model.FTPDetails;
import local.tin.tests.utils.ftp.model.FTPProtocol;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author benitodarder
 */
public class FTPUtilsTest {

    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String PROTOCOL = "ftp";
    private static final String SERVER_NAME = "host"; 
    private static final int PORT = 666;
    private static final String PATH = "path";
    private static final String SAMPLE_URL = PROTOCOL + "://" + USER + ":" + PASSWORD + "@" + SERVER_NAME + ":" + PORT + "/" + PATH;

    @Test
    public void getFTPDetails_contains_protocol() {

        FTPDetails ftpDetails = FTPUtils.getInstance().getFTPDetails(SAMPLE_URL);

        assertThat(ftpDetails.getProtocol(), equalTo(FTPProtocol.valueOf(PROTOCOL.toUpperCase())));    
    }

    @Test
    public void getFTPDetails_contains_user() {

        FTPDetails ftpDetails = FTPUtils.getInstance().getFTPDetails(SAMPLE_URL);

        assertThat(ftpDetails.getUsername(), equalTo(USER));    
    } 
    
    @Test
    public void getFTPDetails_contains_password() {

        FTPDetails ftpDetails = FTPUtils.getInstance().getFTPDetails(SAMPLE_URL);

        assertThat(ftpDetails.getPassword(), equalTo(PASSWORD));    
    }     
    
    @Test
    public void getFTPDetails_contains_host() {

        FTPDetails ftpDetails = FTPUtils.getInstance().getFTPDetails(SAMPLE_URL);

        assertThat(ftpDetails.getServer(), equalTo(SERVER_NAME));    
    }      
    
   @Test
    public void getFTPDetails_contains_port() {

        FTPDetails ftpDetails = FTPUtils.getInstance().getFTPDetails(SAMPLE_URL);

        assertThat(ftpDetails.getPort(), equalTo(PORT));    
    }      
    
    @Test
    public void getFTPDetails_contains_remote_path() {

        FTPDetails ftpDetails = FTPUtils.getInstance().getFTPDetails(SAMPLE_URL);

        assertThat(ftpDetails.getDestinationFolder(), equalTo(PATH));    
    }     
     
}
