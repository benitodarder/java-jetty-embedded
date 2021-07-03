package local.tin.tests.jetty.embedded.core.models.data.interfaces;

/**
 *
 * @author benito.darder
 */
public interface IIdentifiable extends IDataObject {
    
    /**
     * Returns the element identifier, being atomic or composite
     * 
     * @return Object
     */
    public Object getId();
   
    /**
     * Sets the element identifier, being atomic or composite
     * 
     * @param id
     */
    public void setId(Object id);    
    
    
}
