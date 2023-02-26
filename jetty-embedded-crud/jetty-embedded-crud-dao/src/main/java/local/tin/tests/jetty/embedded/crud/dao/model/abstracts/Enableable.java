package local.tin.tests.jetty.embedded.crud.dao.model.abstracts;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import local.tin.tests.jetty.embedded.core.dao.model.interfaces.IEnableable;


/**
 *
 * @author benitodarder
 */
@MappedSuperclass
public abstract class Enableable<K extends Serializable> extends Identifiable<K> implements IEnableable<K> {
   
    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public Boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }    

    
}
