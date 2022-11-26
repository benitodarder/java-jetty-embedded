package local.tin.tests.jetty.embedded.core.models.domain.abstracts;

import java.io.Serializable;
import java.util.Objects;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IIdentifiable;

/**
 *
 * @author benitodarder
 * @param <K>
 */
public abstract class Identifiable<K extends Serializable> implements IIdentifiable<K> {

    private K id;

    @Override
    public K getId() {
        return id;
    }

    @Override
    public void setId(K id) {
        this.id = id;
    }
   
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.getId());
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
        final Identifiable other = (Identifiable) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "View{" + "id=" + id + '}';
    }
    
    
}
