package local.tin.tests.jetty.embedded.core.base;

/**
 *
 * @author benitodarder
 */
public interface IWebPageConfiguration extends IConfiguration {
    
    /**
     * Folder path setting all links root.
     * 
     * @return String. 
     */
    public String getResourceBase();

    /**
     * Default document, a.k.a. index.html .
     * 
     * @return String. 
     */
    public String getDocumentBase();
}

