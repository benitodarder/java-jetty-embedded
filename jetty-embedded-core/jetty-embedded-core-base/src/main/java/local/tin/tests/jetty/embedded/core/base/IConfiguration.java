package local.tin.tests.jetty.embedded.core.base;

import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;

/**
 *
 * @author benitodarder
 */
public interface IConfiguration {
    
    /**
     * From classic web.xml:
     * 
     * \<servlet-mapping\>...\<url-pattern\>
     * 
     * @return String 
     */
    public abstract String getURLPattern();
    
    /**
     * http://\<server name\>:\<http port\>/\<Base application path\>...
     * 
     * @return String
     */
    public abstract String getApplicationPath();
    
    /**
     * http://\<server name\>:\<http port\>/\<Base application path\>...
     * 
     * @return String
     */    
    public abstract int getHttpPort();
    
    /**
     * Semicolon separated string with controller classes
     * canonical names.
     * 
     * Do no forget to include MultiPartFeature.class when default core Ping
     * facade with Multipart method.
     * 
     * @return String
     */    
    public abstract String getControllers();

    /**
     * Returns a ShutdownHook for cleanup operations.
     * 
     * @return S
     */
    public abstract ShutdownHook getShutdownHook(); 
}
