package local.tin.tests.jetty.embedded.core.dao.model.interfaces;

import java.io.Serializable;

/**
 *
 * @author benito.darder
 * @param <K>
 */
public interface INamedEnableable<K extends Serializable> extends IEnableable<K> {
    
    /**
     * Returns the element name
     * 
     * @return String
     */
    public String getName();
   
    /**
     * Sets the element name
     * 
     * @param name
     */
    public void setName(String name);    
        
}
