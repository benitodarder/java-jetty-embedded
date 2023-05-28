package local.tin.tests.jetty.embedded.core.base;

/**
 *
 * @author benitodarder
 */
public interface ILoggingConfiguration extends IConfiguration {
    
    /**
     * Get logging filter class for request/response messaging.
     * 
     * @return Class.
     */
    public Class getLoggingFilterClass();
    
    /**
     * Get logging filter URL path to apply.
     * 
     * @return String.
     */
    public String getLogginfFilterPath();
    
    /**
     * Get Http access log log4j.xml/properties logger.
     * 
     * @return String 
     */
    public String getHttpAccessLogger();
    
    /**
     * Get log line pattern for Http access logger.
     * 
     * @return String. 
     */
    public String getHttpAccessPattern();
    
}
