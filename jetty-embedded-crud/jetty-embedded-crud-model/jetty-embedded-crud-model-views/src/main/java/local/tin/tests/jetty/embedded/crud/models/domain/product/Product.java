package local.tin.tests.jetty.embedded.crud.models.domain.product;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import local.tin.tests.jetty.embedded.crud.models.domain.abstracts.AbstractNamedProduct;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product extends AbstractNamedProduct<Integer> {
    
    private String description;
    @XmlElementWrapper(name="assemblies")
    @XmlElementRef     
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

    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", name=" + getName() + ", description=" + description + ", assemblies=" + assemblies + '}';
    }    

}
