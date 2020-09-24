package local.tin.tests.utils.ftp;

import local.tin.tests.utils.ftp.utils.FTPConnections;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import local.tin.tests.utils.ftp.model.FTPDetails;
import local.tin.tests.utils.ftp.model.FTPUtilsException;
import local.tin.tests.utils.ftp.utils.FTPUtils;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import org.junit.Test;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
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
@PrepareForTest({FTPConnections.class, FTPUtils.class})
public class SimpleFTPClientTest {

    private static final String SAMPLE_URL = "sample url";
    private static final String FILE_TO_UPLOAD = "file to upload";
    private static final String UPLOAD_STREAM_NAME = "Upload stream name";
    private static final boolean OPERATION_RESULT = true;
    private static final String DESTINATION_FOLDER = "destination folder";
    private static FTPConnections mockedFTPConnections;
    private static FTPUtils mockedFTPUtils;
    private FTPClient mockedFTPClient;
    private FTPDetails fTPDetails;
    private InputStream mockedStream;
    private SSHClient mockedSHClient;
    private SFTPClient mockedSFTPClient;

    @BeforeClass
    public static void setUpClass() {
        mockedFTPConnections = mock(FTPConnections.class);
        mockedFTPUtils = mock(FTPUtils.class);
    }

    @Before
    public void setUp() {
        reset(mockedFTPUtils);
        reset(mockedFTPConnections);
        fTPDetails = new FTPDetails();
        mockedStream = mock(InputStream.class);
    }

    public void setFTPMocks() throws FTPUtilsException {
        setFTPConnectionsMock();
        mockedFTPClient = mock(FTPClient.class);
        when(mockedFTPConnections.getFTPClient(fTPDetails)).thenReturn(mockedFTPClient);
        setUploadStreamMocks();
    }

    public void setUploadStreamMocks() {
        fTPDetails.setSourceStream(mockedStream);
        fTPDetails.setDestinationFileName(UPLOAD_STREAM_NAME);
    }

    public void setFTPConnectionsMock() {
        PowerMockito.mockStatic(FTPConnections.class);
        when(FTPConnections.getInstance()).thenReturn(mockedFTPConnections);
    }

    @Test
    public void sendFileFTP_retrieves_connection() throws FTPUtilsException {
        setFTPMocks();

        SimpleFTPClient.getInstance().sendFileFTP(fTPDetails);

        verify(mockedFTPConnections).getFTPClient(fTPDetails);
    }

    @Test
    public void sendFileFTP_uploads_stream_with_given_name() throws IOException, FTPUtilsException, Exception {
        setFTPMocks();

        SimpleFTPClient.getInstance().sendFileFTP(fTPDetails);

        verify(mockedFTPClient).storeFile(UPLOAD_STREAM_NAME, mockedStream);
    }

    @Test
    public void sendFileFTP_returns_storefile_result() throws IOException, FTPUtilsException, Exception {
        setFTPMocks();
        when(mockedFTPClient.storeFile(UPLOAD_STREAM_NAME, mockedStream)).thenReturn(OPERATION_RESULT);

        boolean result = SimpleFTPClient.getInstance().sendFileFTP(fTPDetails);

        assertThat(result, equalTo(OPERATION_RESULT));
    }

    @Test
    public void sendFileFTP_disconnects() throws IOException, FTPUtilsException, Exception {
        setFTPMocks();
        when(mockedFTPClient.storeFile(UPLOAD_STREAM_NAME, mockedStream)).thenReturn(OPERATION_RESULT);

        boolean result = SimpleFTPClient.getInstance().sendFileFTP(fTPDetails);

        verify(mockedFTPClient).disconnect();
    }

    @Test
    public void sendFileSFTP_retrieves_sshclient() throws FTPUtilsException {
        setSFTPMocks();

        SimpleFTPClient.getInstance().sendFileSFTP(fTPDetails);

        verify(mockedFTPConnections).getSSHClient(fTPDetails);
    }

    public void setSFTPMocks() throws FTPUtilsException {
        setFTPConnectionsMock();
        mockedSHClient = mock(SSHClient.class);
        mockedSFTPClient = mock(SFTPClient.class);
        when(mockedFTPConnections.getSSHClient(fTPDetails)).thenReturn(mockedSHClient);
        when(mockedFTPConnections.getSFTPClient(mockedSHClient)).thenReturn(mockedSFTPClient);
        fTPDetails.setSourceFilePathAndName(FILE_TO_UPLOAD);
        fTPDetails.setDestinationFileName(UPLOAD_STREAM_NAME);
    }

    @Test
    public void sendFileSFTP_retrieves_sftclient() throws FTPUtilsException {
        setSFTPMocks();

        SimpleFTPClient.getInstance().sendFileSFTP(fTPDetails);

        verify(mockedFTPConnections).getSFTPClient(mockedSHClient);
    }

    @Test
    public void sendFileSFTP_puts_source_with_given_name() throws FTPUtilsException, IOException {
        setSFTPMocks();

        SimpleFTPClient.getInstance().sendFileSFTP(fTPDetails);

        verify(mockedSFTPClient).put(FILE_TO_UPLOAD, UPLOAD_STREAM_NAME);
    }

    @Test
    public void sendFileSFTP_closes_and_disconnects() throws FTPUtilsException, IOException {
        setSFTPMocks();

        SimpleFTPClient.getInstance().sendFileSFTP(fTPDetails);

        verify(mockedSFTPClient).close();
        verify(mockedSHClient).disconnect();
    }

    @Test
    public void sendFileFTP_by_URL_generates_the_corresponding_ftp_details() throws FTPUtilsException {
        setSendFileByURLMocks();

        SimpleFTPClient.getInstance().sendFileFTP(SAMPLE_URL, UPLOAD_STREAM_NAME, mockedStream);

        verify(mockedFTPUtils).getFTPDetails(SAMPLE_URL);
    }

    @Test
    public void sendFileFTP_by_URL_assings_destination_file_name() throws FTPUtilsException {
        setSendFileByURLMocks();

        SimpleFTPClient.getInstance().sendFileFTP(SAMPLE_URL, UPLOAD_STREAM_NAME, mockedStream);

        verify(fTPDetails).setDestinationFileName(UPLOAD_STREAM_NAME);
    }

    private void setSendFileByURLMocks() throws FTPUtilsException {
        PowerMockito.mockStatic(FTPUtils.class);
        when(FTPUtils.getInstance()).thenReturn(mockedFTPUtils);
        fTPDetails = mock(FTPDetails.class);
        when(mockedFTPUtils.getFTPDetails(SAMPLE_URL)).thenReturn(fTPDetails);
        setFTPConnectionsMock();
        mockedFTPClient = mock(FTPClient.class);
        when(mockedFTPConnections.getFTPClient(fTPDetails)).thenReturn(mockedFTPClient);
        mockedSHClient = mock(SSHClient.class);
        mockedSFTPClient = mock(SFTPClient.class);
        when(mockedFTPConnections.getSSHClient(fTPDetails)).thenReturn(mockedSHClient);
        when(mockedFTPConnections.getSFTPClient(mockedSHClient)).thenReturn(mockedSFTPClient);
    }

    @Test
    public void sendFileFTP_by_URL_assings_input_stream() throws FTPUtilsException {
        setSendFileByURLMocks();

        SimpleFTPClient.getInstance().sendFileFTP(SAMPLE_URL, UPLOAD_STREAM_NAME, mockedStream);

        verify(fTPDetails).setSourceStream(mockedStream);
    }

    @Test
    public void sendFileFTP_by_URL_retrieves_connection() throws FTPUtilsException {
        setSendFileByURLMocks();

        SimpleFTPClient.getInstance().sendFileFTP(SAMPLE_URL, UPLOAD_STREAM_NAME, mockedStream);

        verify(mockedFTPConnections).getFTPClient(fTPDetails);
    }

    @Test
    public void sendFileSFTP_by_URL_generates_the_corresponding_ftp_details() throws FTPUtilsException {
        setSendFileByURLMocks();

        SimpleFTPClient.getInstance().sendFileSFTP(SAMPLE_URL, UPLOAD_STREAM_NAME, FILE_TO_UPLOAD);

        verify(mockedFTPUtils).getFTPDetails(SAMPLE_URL);
    }

    @Test
    public void sendFileSFTP_by_URL_assings_destination_file_name() throws FTPUtilsException {
        setSendFileByURLMocks();

        SimpleFTPClient.getInstance().sendFileSFTP(SAMPLE_URL, UPLOAD_STREAM_NAME, FILE_TO_UPLOAD);

        verify(fTPDetails).setDestinationFileName(UPLOAD_STREAM_NAME);
    }

    @Test
    public void sendFileSFTP_by_URL_assings_source_file_name() throws FTPUtilsException {
        setSendFileByURLMocks();

        SimpleFTPClient.getInstance().sendFileSFTP(SAMPLE_URL, UPLOAD_STREAM_NAME, FILE_TO_UPLOAD);

        verify(fTPDetails).setSourceFilePathAndName(FILE_TO_UPLOAD);
    }

    @Test
    public void sendFileSFTP_by_url_retrieves_sshclient() throws FTPUtilsException {
        setSendFileByURLMocks();

        SimpleFTPClient.getInstance().sendFileSFTP(SAMPLE_URL, UPLOAD_STREAM_NAME, FILE_TO_UPLOAD);

        verify(mockedFTPConnections).getSSHClient(fTPDetails);
    }

    @Test
    public void sendFileFTP_adds_destination_folder_when_present() throws IOException, FTPUtilsException, Exception {
        setFTPMocks();
        fTPDetails.setDestinationFolder(DESTINATION_FOLDER);

        SimpleFTPClient.getInstance().sendFileFTP(fTPDetails);

        verify(mockedFTPClient).storeFile(DESTINATION_FOLDER + SimpleFTPClient.FTP_PATH_SEPARATOR + UPLOAD_STREAM_NAME, mockedStream);
    }

    @Test
    public void sendFileSFTP_adds_destination_folder_when_present() throws FTPUtilsException, IOException {
        setSFTPMocks();
        fTPDetails.setDestinationFolder(DESTINATION_FOLDER);
        
        SimpleFTPClient.getInstance().sendFileSFTP(fTPDetails);

        verify(mockedSFTPClient).put(FILE_TO_UPLOAD, DESTINATION_FOLDER + SimpleFTPClient.FTP_PATH_SEPARATOR + UPLOAD_STREAM_NAME);
    }
}
