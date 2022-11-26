package local.tin.tests.jetty.embedded.core.models.domain.interfaces;

import java.io.Serializable;

/**
 *
 * @author benito.darder
 * @param <K>
 */
public interface IIdentifiable<K extends Serializable> extends IView {
    
    /**
     * Returns the element identifier, being atomic or composite
     * 
     * @return Object
     */
    public K getId();
   
    /**
     * Sets the element identifier, being atomic or composite
     * 
     * @param id
     */
    public void setId(K id);    
    
}
