package local.tin.tests.utils.ftp.utils;

import local.tin.tests.utils.ftp.utils.FTPConnections;
import local.tin.tests.utils.ftp.model.FTPConnection;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.apache.commons.net.ftp.FTPClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author benitodarder
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({org.apache.commons.net.ftp.FTPClient.class, FTPConnections.class, SSHClient.class, SFTPClient.class, PromiscuousVerifier.class})
public class FTPConnectionsTest {

    private static final String SERVER_NAME = "server";
    private static final int SERVER_PORT = 666;
    private static final String USERNAME = "username...";
    private static final String PASSWORD = "pass";
    private FTPClient mockedFTPClient;
    private FTPConnection fTPConnection;
    private SSHClient mockedSSHClient;
    private SFTPClient mockedSFTPClient;
    private PromiscuousVerifier mockedPromiscuousVerifier;

    @Before
    public void setUp() {
        fTPConnection = new FTPConnection();
        fTPConnection.setUsername(USERNAME);
        fTPConnection.setPassword(PASSWORD);
        fTPConnection.setServer(SERVER_NAME);
        fTPConnection.setPort(SERVER_PORT);
    }

    @Test
    public void getFTPClient_returns_expected_client() throws Exception {
        mockedFTPClient = mock(FTPClient.class);
        PowerMockito.whenNew(FTPClient.class).withNoArguments().thenReturn(mockedFTPClient);

        FTPClient fTPClient = FTPConnections.getInstance().getFTPClient(fTPConnection);

        verify(mockedFTPClient).connect(SERVER_NAME, SERVER_PORT);
        verify(mockedFTPClient).login(USERNAME, PASSWORD);
    }

    @Test
    public void getsSHClient_returns_expected_client() throws Exception {
        mockedPromiscuousVerifier = mock(PromiscuousVerifier.class);
        PowerMockito.whenNew(PromiscuousVerifier.class).withNoArguments().thenReturn(mockedPromiscuousVerifier);
        mockedSSHClient = mock(SSHClient.class);
        PowerMockito.whenNew((SSHClient.class)).withNoArguments().thenReturn(mockedSSHClient);
        mockedSFTPClient = mock(SFTPClient.class);
        when(mockedSSHClient.newSFTPClient()).thenReturn(mockedSFTPClient);

        SSHClient result = FTPConnections.getInstance().getSSHClient(fTPConnection);

        verify(mockedSSHClient).addHostKeyVerifier(mockedPromiscuousVerifier);
        verify(mockedSSHClient).connect(SERVER_NAME);
        verify(mockedSSHClient).authPassword(USERNAME, PASSWORD);
        assertThat(result, equalTo(mockedSSHClient));
    }
    
    @Test
    public void getSFTPClient_returns_expected_client() throws Exception {
        mockedSSHClient = mock(SSHClient.class);
        mockedSFTPClient = mock(SFTPClient.class);
        when(mockedSSHClient.newSFTPClient()).thenReturn(mockedSFTPClient);

        SFTPClient result = FTPConnections.getInstance().getSFTPClient(mockedSSHClient);

        verify(mockedSSHClient).newSFTPClient();
        assertThat(result, equalTo(mockedSFTPClient));
    }    
}
