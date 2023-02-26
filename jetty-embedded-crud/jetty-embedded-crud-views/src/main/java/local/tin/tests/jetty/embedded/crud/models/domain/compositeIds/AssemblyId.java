package local.tin.tests.jetty.embedded.crud.models.domain.compositeIds;

import java.util.Objects;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.ICompositeId;


/**
 *
 * @author benito.darder
 */
public class AssemblyId implements ICompositeId {
    
    private Integer productId;
    private Integer componentId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer product) {
        this.productId = product;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer component) {
        this.componentId = component;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.productId);
        hash = 47 * hash + Objects.hashCode(this.componentId);
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
        final AssemblyId other = (AssemblyId) obj;
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        return Objects.equals(this.componentId, other.componentId);
    }

    @Override
    public String toString() {
        return "AssemblyId{" + "product=" + productId + ", component=" + componentId + '}';
    }
}
