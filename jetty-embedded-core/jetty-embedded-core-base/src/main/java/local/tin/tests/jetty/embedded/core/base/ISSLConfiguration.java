package local.tin.tests.jetty.embedded.core.base;

/**
 *
 * @author benitodarder
 */
public interface ISSLConfiguration extends IConfiguration {
    
    /**
     * Certificate store file location.
     * 
     * @return String
     */
    public String getJKSPath();

    /**
     * Keystore password.
     * 
     * @return String
     */
    public String getKeystorePassword();

    /**
     * JKS password.
     * 
     * @return String
     */
    public String getJKSPassword();
}
