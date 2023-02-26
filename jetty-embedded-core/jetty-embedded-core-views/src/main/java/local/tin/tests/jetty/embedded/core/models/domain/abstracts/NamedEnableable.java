package local.tin.tests.jetty.embedded.core.models.domain.abstracts;

import java.io.Serializable;
import java.util.Objects;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.INamedEnableable;


/**
 *
 * @author benitodarder
 * @param <K>
 */
public abstract class NamedEnableable<K extends Serializable> extends Enableable<K> implements INamedEnableable<K> {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 67 * hash + Objects.hashCode(this.name);
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
        final NamedEnableable other = (NamedEnableable) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
        }        
        return super.equals(obj);
    }
    
    

}
