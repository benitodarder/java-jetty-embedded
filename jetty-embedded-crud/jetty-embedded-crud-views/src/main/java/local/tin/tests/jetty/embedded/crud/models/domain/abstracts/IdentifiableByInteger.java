package local.tin.tests.jetty.embedded.crud.models.domain.abstracts;

import java.util.Objects;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IIdentifiable;

/**
 *
 * @author benitodarder
 * @param <K>
 */
public abstract class IdentifiableByInteger implements IIdentifiable<Integer> {
  

    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
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
        final IdentifiableByInteger other = (IdentifiableByInteger) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "Identifiable{" + "id=" + getId() + '}';
    }
    
    
}
