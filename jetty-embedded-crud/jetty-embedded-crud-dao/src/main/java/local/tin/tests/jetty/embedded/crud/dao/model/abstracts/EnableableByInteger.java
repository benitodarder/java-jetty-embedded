package local.tin.tests.jetty.embedded.crud.dao.model.abstracts;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import local.tin.tests.jetty.embedded.core.dao.model.interfaces.IEnableable;


/**
 *
 * @author benitodarder
 */
@MappedSuperclass
public abstract class EnableableByInteger extends IdenifiableByInteger implements IEnableable<Integer> {
   
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

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + (this.enabled ? 1 : 0);
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
        final EnableableByInteger other = (EnableableByInteger) obj;
        if (this.enabled != other.enabled) {
            return false;
        }
        return super.equals(obj);
    }

    
}
