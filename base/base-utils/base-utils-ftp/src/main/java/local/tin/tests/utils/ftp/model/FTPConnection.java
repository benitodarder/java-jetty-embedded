package local.tin.tests.utils.ftp.model;

/**
 *
 * @author benitodarder
 */
public class FTPConnection {
    
    private FTPProtocol protocol;
    private String server;
    private int port;
    private String username;
    private String password;
    private boolean annonymous;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnnonymous() {
        return annonymous;
    }

    public void setAnnonymous(boolean annonymous) {
        this.annonymous = annonymous;
    }

    public FTPProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(FTPProtocol protocol) {
        this.protocol = protocol;
    }

    
    
}
