package local.tin.tests.model.data.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import local.tin.tests.model.data.abstracts.AbstractNamed;

/**
 *
 * @author benito.darder
 */
@Entity
@Table(name = "COMPONENT")
public class Component extends AbstractNamed {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unitId", referencedColumnName = "id")
    private Unit unit;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "component")
    private Set<Assembly> assemblies;   
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Integer) id;
    }

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
