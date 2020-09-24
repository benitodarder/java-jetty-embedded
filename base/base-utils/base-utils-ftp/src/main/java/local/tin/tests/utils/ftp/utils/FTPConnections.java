package local.tin.tests.utils.ftp.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.tin.tests.utils.ftp.model.FTPConnection;
import local.tin.tests.utils.ftp.model.FTPUtilsException;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author benitodarder
 */
public class FTPConnections {

    public static final String JSCH_SFTP_CHANNEL = "sftp";

    private FTPConnections() {
    }

    public static FTPConnections getInstance() {
        return FTPConnectionsHolder.INSTANCE;
    }

    private static class FTPConnectionsHolder {

        private static final FTPConnections INSTANCE = new FTPConnections();
    }

    /**
     * Returns the corresponding FTPClient for given credentials.
     *
     * @param ftpConnection
     * @return FTPClient
     * @throws FTPUtilsException
     */
    public FTPClient getFTPClient(FTPConnection ftpConnection) throws FTPUtilsException {
        FTPClient ftp = null;
        try {
            ftp = new FTPClient();
            ftp.connect(ftpConnection.getServer(), ftpConnection.getPort());
            ftp.login(ftpConnection.getUsername(), ftpConnection.getPassword());
        } catch (IOException ex) {
            throw new FTPUtilsException(ex);
        }
        return ftp;
    }

    public SFTPClient getSFTPClient(SSHClient sSHClient) throws FTPUtilsException {
        SFTPClient sFTPClient = null;
        try {
            sFTPClient = sSHClient.newSFTPClient();
        } catch (IOException ex) {
             throw new FTPUtilsException(ex);
        }
        return sFTPClient;
    }
    
    public SSHClient getSSHClient(FTPConnection fTPConnection) throws FTPUtilsException {
        SSHClient sSHClient = null;
        try {
            sSHClient = new SSHClient();
            sSHClient.addHostKeyVerifier(new PromiscuousVerifier());
            sSHClient.connect(fTPConnection.getServer());
            sSHClient.authPassword(fTPConnection.getUsername(), fTPConnection.getPassword());
        } catch (IOException ex) {
             throw new FTPUtilsException(ex);
        }
        return sSHClient;
    }    
}
