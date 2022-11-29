package local.tin.tests.jetty.embedded.core.models.data.abstracts;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import local.tin.tests.jetty.embedded.core.models.data.interfaces.INamed;

/**
 *
 * @author benitodarder
 */
@MappedSuperclass
public abstract class NamedByInteger extends EnableableByInteger implements INamed<Integer> {

    @Column(name = "name")
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
        final NamedByInteger other = (NamedByInteger) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
        }        
        return super.equals(obj);
    }
    
    

}
