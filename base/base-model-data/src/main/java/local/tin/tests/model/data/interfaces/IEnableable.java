package local.tin.tests.model.data.interfaces;

/**
 *
 * @author benitodarder
 */
public interface IEnableable extends IDataObject {
    
    /**
     * Returns enabled/disabled status
     * 
     * @return boolean
     */
    public boolean isEnabled();
    
    /**
     * Sets enabled/disabled status
     * 
     * @param enabled boolean
     */
    public void setEnabled(boolean enabled);
    
}
