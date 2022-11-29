package local.tin.tests.jetty.embedded.crud.models.data.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import local.tin.tests.jetty.embedded.core.models.data.abstracts.NamedByInteger;


/**
 *
 * @author benito.darder
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends NamedByInteger {
    
    @Column(name = "description")
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Assembly> assemblies;      

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
