package local.tin.tests.jetty.embedded.crud.models.domain.product;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import local.tin.tests.jetty.embedded.crud.models.domain.abstracts.AbstractProduct;
import local.tin.tests.jetty.embedded.crud.models.domain.compositeIds.AssemblyId;
import local.tin.tests.jetty.embedded.crud.models.domain.deserializers.AssemblyDeserializer;
import local.tin.tests.jetty.embedded.crud.models.domain.deserializers.AssemblyIdDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonDeserialize(using = AssemblyDeserializer.class)
public class Assembly extends AbstractProduct {

    @JsonDeserialize(using = AssemblyIdDeserializer.class)
    private AssemblyId id;
    private Double quantity;
    private Product product;
    private Component component;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public AssemblyId getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (AssemblyId) id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.quantity);
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
        final Assembly other = (Assembly) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.quantity, other.quantity);
    }

    @Override
    public String toString() {
        return "Assemby{" + "id=" + id + ", qunatity=" + quantity + '}';
    }

}
