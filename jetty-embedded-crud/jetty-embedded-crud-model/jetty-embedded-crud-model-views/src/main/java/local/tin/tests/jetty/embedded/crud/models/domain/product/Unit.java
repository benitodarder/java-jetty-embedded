package local.tin.tests.jetty.embedded.crud.models.domain.product;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import local.tin.tests.jetty.embedded.crud.models.domain.abstracts.NamedEntityByInteger;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Unit extends NamedEntityByInteger {

    private String abbreviation;
    @XmlElementWrapper(name="components")
    @XmlElementRef     
    private Set<Component> components; 

    @Override
    public String toString() {
        return "Unit{" + "id=" + getId() + ", name=" + getName() + '}';
    }   

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
    public Set<Component> getComponents() {
        if (components == null) {
            components = new HashSet<>();
        }
        return components;
    }

    public void setComponents(Set<Component> components) {
        this.components = components;
    }    


}
