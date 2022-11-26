package local.tin.tests.jetty.embedded.core.models.domain.abstracts;

import java.io.Serializable;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IEnableable;

/**
 *
 * @author benitodarder
 */
public abstract class Enableable<K extends Serializable> extends Identifiable<K> implements IEnableable<K> {
    
    private boolean enabled;

    @Override
    public  Boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }    

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 47 * hash + (this.enabled ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enableable<?> other = (Enableable<?>) obj;
        if (this.enabled != other.enabled) {
            return false;
        }
        return super.equals(obj);
    }

    
}
