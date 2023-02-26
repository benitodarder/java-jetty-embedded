package local.tin.tests.jetty.embedded.crud.dao.model.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import local.tin.tests.jetty.embedded.core.models.data.abstracts.NamedEnableableByInteger;

/**
 *
 * @author benito.darder
 */
@Entity
@Table(name = "COMPONENT")
public class Component extends NamedEnableableByInteger {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unitId", referencedColumnName = "id")
    private Unit unit;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "component")
    private Set<Assembly> assemblies;   

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Set<Assembly> getAssemblies() {
        if (assemblies == null) {
            assemblies = new HashSet<>();
        }
        return assemblies;
    }

    public void setAssemblies(Set<Assembly> assemblies) {
        this.assemblies = assemblies;
    }

    
}
