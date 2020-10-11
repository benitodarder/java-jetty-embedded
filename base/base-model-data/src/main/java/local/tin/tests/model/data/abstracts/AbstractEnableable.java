package local.tin.tests.model.data.abstracts;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import local.tin.tests.model.data.interfaces.IEnableable;

/**
 *
 * @author benitodarder
 */
@MappedSuperclass
public abstract class AbstractEnableable extends AbstractIdentifiable implements IEnableable {
   
    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }    

    
}
