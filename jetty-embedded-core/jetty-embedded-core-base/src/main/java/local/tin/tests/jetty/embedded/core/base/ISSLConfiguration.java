package local.tin.tests.jetty.embedded.core.base;

/**
 *
 * @author benitodarder
 */
public interface ISSLConfiguration extends IConfiguration {
    
    public String getJKSPath();

    public String getKeystorePassword();

    public String getJKSPassword();
}
