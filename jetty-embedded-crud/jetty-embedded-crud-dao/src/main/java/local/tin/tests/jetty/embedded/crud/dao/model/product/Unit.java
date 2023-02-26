package local.tin.tests.jetty.embedded.crud.dao.model.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import local.tin.tests.jetty.embedded.crud.dao.model.abstracts.NamedEnableableByInteger;

/**
 *
 * @author benito.darder
 */
@Entity
@Table(name = "UNIT")
public class Unit extends NamedEnableableByInteger {


    @Column(name = "abbreviation")
    private String abbreviation;
    @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY)
    private Set<Component> components;    
    

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
