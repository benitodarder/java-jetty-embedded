package local.tin.tests.jetty.embedded.core.models.domain.messaging;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import local.tin.tests.jetty.embedded.core.models.domain.abstracts.View;
import local.tin.tests.jetty.embedded.core.models.domain.abstracts.Identifiable;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IIdentifiable;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IMessage;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({View.class})
public abstract class Request<K extends IIdentifiable> implements IMessage {

    private String modelClass;
    @XmlElementRef
    private K item;

    public String getModelClass() {
        return modelClass;
    }

    public void setModelClass(String modelClass) {
        this.modelClass = modelClass;
    }

    public K getItem() {
        return item;
    }

    public void setItem(K item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.modelClass);
        hash = 89 * hash + Objects.hashCode(this.item);
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
        final Request other = (Request) obj;
        if (!Objects.equals(this.modelClass, other.modelClass)) {
            return false;
        }
        return Objects.equals(this.item, other.item);
    }
}
