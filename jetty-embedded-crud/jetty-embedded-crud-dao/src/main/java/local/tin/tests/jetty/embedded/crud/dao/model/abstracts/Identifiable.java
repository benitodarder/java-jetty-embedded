package local.tin.tests.jetty.embedded.crud.dao.model.abstracts;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.MappedSuperclass;
import local.tin.tests.jetty.embedded.core.dao.model.interfaces.IIdentifiable;

/**
 *
 * @author benitodarder
 * @param <K>
 */
@MappedSuperclass
public abstract class Identifiable<K extends Serializable> implements IIdentifiable<K> {

  
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getId());
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

}
