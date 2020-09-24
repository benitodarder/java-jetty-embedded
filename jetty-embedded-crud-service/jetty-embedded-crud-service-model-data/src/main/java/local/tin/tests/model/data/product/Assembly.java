package local.tin.tests.model.data.product;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import local.tin.tests.model.data.abstracts.AbstractIdentifiable;
import local.tin.tests.model.data.embeddables.AssemblyId;

/**
 *
 * @author benito.darder
 */
@Entity
@Table(name = "ASSEMBLY")
public class Assembly extends AbstractIdentifiable {

    @EmbeddedId
    private AssemblyId id;
    @Column(name = "quantity")
    private Double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)     
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "componentId", referencedColumnName = "id", insertable = false, updatable = false)     
    private Component component;
    

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
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.quantity);
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
    
    
}
