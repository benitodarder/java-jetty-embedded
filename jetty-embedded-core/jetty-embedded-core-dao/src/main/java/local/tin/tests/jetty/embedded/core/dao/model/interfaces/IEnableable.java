package local.tin.tests.jetty.embedded.core.dao.model.interfaces;

import java.io.Serializable;

/**
 *
 * @author benitodarder
 * @param <K>
 */
public interface IEnableable<K extends Serializable> extends IIdentifiable<K> {
    
    /**
     * Returns enabled/disabled status
     * 
     * @return boolean
     */
    public Boolean isEnabled();
    
    /**
     * Sets enabled/disabled status
     * 
     * @param enabled boolean
     */
    public void setEnabled(Boolean enabled);
    
}
