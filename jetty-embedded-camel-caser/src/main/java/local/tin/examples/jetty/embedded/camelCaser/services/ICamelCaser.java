package local.tin.examples.jetty.embedded.camelCaser.services;

/**
 *
 * @author benitodarder
 */
public interface ICamelCaser {
    
    /**
     * Returns source formated as camel case.
     * 
     * @param source as String
     * @return String
     */
    public String transform(String source);
}
