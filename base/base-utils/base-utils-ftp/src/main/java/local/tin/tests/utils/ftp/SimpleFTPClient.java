package local.tin.tests.utils.ftp;

import local.tin.tests.utils.ftp.utils.FTPConnections;
import java.io.IOException;
import java.io.InputStream;
import local.tin.tests.utils.ftp.model.FTPDetails;
import local.tin.tests.utils.ftp.model.FTPUtilsException;
import local.tin.tests.utils.ftp.utils.FTPUtils;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class SimpleFTPClient {

    public static final String FTP_PATH_SEPARATOR = "/";    
    private static final Logger LOGGER = Logger.getLogger(SimpleFTPClient.class);

    private SimpleFTPClient() {
    }

    public static SimpleFTPClient getInstance() {
        return FtpClientHolder.INSTANCE;
    }

    private static class FtpClientHolder {

        private static final SimpleFTPClient INSTANCE = new SimpleFTPClient();
    }

    /**
     * Uploads given InputStream with the corresponding name to the FTP stated
     * by the credentials.
     *
     * Returns true when successful, false otherwise
     *
     * @param ftpDetails
     * @return boolean
     * @throws FTPUtilsException
     */
    public boolean sendFileFTP(FTPDetails ftpDetails) throws FTPUtilsException {
        boolean result = false;
        FTPClient ftp = null;
        try {
            ftp = FTPConnections.getInstance().getFTPClient(ftpDetails);
            result = ftp.storeFile(getDestinaionPathAndFileName(ftpDetails), ftpDetails.getSourceStream());
        } catch (IOException ex) {
            throw new FTPUtilsException(ex);
        } finally {
            if (ftp != null) {
                try {
                    ftp.disconnect();
                } catch (IOException ex) {
                    LOGGER.error(ex.getLocalizedMessage());
                    LOGGER.debug(ex);
                }
            }
        }
        return result;
    }

    private String getDestinaionPathAndFileName(FTPDetails ftpDetails) {
        StringBuilder stringBuilder = new StringBuilder();
        if (ftpDetails.getDestinationFolder() != null && !ftpDetails.getDestinationFolder().isEmpty()) {
            stringBuilder.append(ftpDetails.getDestinationFolder()).append(FTP_PATH_SEPARATOR);
        }
        stringBuilder.append(ftpDetails.getDestinationFileName());
        return stringBuilder.toString();
    }

    /**
     * Uploads the file stated the string by FTPDetails.fileToUpload with the
     * corresponding name
     *
     * @param fTPDetails
     * @throws FTPUtilsException
     */
    public void sendFileSFTP(FTPDetails fTPDetails) throws FTPUtilsException {
        SSHClient sshClient = null;
        SFTPClient sFTPClient = null;
        try {
            sshClient = FTPConnections.getInstance().getSSHClient(fTPDetails);
            sFTPClient = FTPConnections.getInstance().getSFTPClient(sshClient);
            sFTPClient.put(fTPDetails.getSourceFilePathAndName(), getDestinaionPathAndFileName(fTPDetails));
        } catch (IOException ex) {
            throw new FTPUtilsException(ex);

        } finally {
            if (sFTPClient != null) {
                try {
                    sFTPClient.close();
                } catch (IOException ex) {
                    LOGGER.error(ex.getLocalizedMessage());
                    LOGGER.debug(ex);
                }
            }
            if (sshClient != null) {
                try {
                    sshClient.disconnect();
                } catch (IOException ex) {
                    LOGGER.error(ex.getLocalizedMessage());
                    LOGGER.debug(ex);
                }
            }
        }
    }

    /**
     * Uploads given InputStream with the corresponding name to the FTP stated
     * by the URL string.
     *
     * Returns true when successful, false otherwise
     *
     * @param ftpURL
     * @param destinationFileName
     * @param inputStream
     * @return boolean
     * @throws FTPUtilsException
     */
    public boolean sendFileFTP(String ftpURL, String destinationFileName, InputStream inputStream) throws FTPUtilsException {
        FTPDetails ftpDetails = getFTPDetailsFromURL(ftpURL, destinationFileName);
        ftpDetails.setSourceStream(inputStream);
        return sendFileFTP(ftpDetails);
    }

    private FTPDetails getFTPDetailsFromURL(String ftpURL, String destinationFileName) {
        FTPDetails ftpDetails = FTPUtils.getInstance().getFTPDetails(ftpURL);
        ftpDetails.setDestinationFileName(destinationFileName);
        return ftpDetails;
    }

    /**
     * Uploads the file stated the corresponding argument with the given name to
     * the FTP location given by the URL
     *
     * @param ftpURL
     * @param destinationFileName
     * @param sourceFilePathAndName
     * @throws FTPUtilsException
     */
    public void sendFileSFTP(String ftpURL, String destinationFileName, String sourceFilePathAndName) throws FTPUtilsException {
        FTPDetails ftpDetails = getFTPDetailsFromURL(ftpURL, destinationFileName);
        ftpDetails.setSourceFilePathAndName(sourceFilePathAndName);
        sendFileSFTP(ftpDetails);
    }

}
