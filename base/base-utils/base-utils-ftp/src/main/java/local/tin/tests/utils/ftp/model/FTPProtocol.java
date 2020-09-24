package local.tin.tests.utils.ftp.model;

/**
 *
 * @author benitodarder
 */
public enum FTPProtocol {
    
    FTP("ftp"),
    SFTP("sftp");
    
    private final String protocol;

    private FTPProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    public String getProtocol() {
        return protocol;
    }
    
}
