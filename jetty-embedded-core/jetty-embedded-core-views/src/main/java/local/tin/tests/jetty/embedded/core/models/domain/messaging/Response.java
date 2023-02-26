package local.tin.tests.jetty.embedded.core.models.domain.messaging;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import local.tin.tests.jetty.embedded.core.models.domain.errors.CommonError;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IIdentifiable;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IMessage;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IView;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({IView.class})
public abstract class Response<K extends IIdentifiable> implements IMessage {
    
    private boolean success;
    @XmlElementWrapper(name="data")
    @XmlElementRef
    private Collection<K> responses;
    @XmlElementRef
    private CommonError error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Collection<K> getResponses() {
        return responses;
    }

    public void setResponses(Collection<K> responses) {
        this.responses = responses;
    }

    public CommonError getError() {
        return error;
    }

    public void setError(CommonError error) {
        this.error = error;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.success ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.responses);
        hash = 79 * hash + Objects.hashCode(this.error);
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
        final Response other = (Response) obj;
        if (this.success != other.success) {
            return false;
        }
        if (!Objects.equals(this.responses, other.responses)) {
            return false;
        }
        return Objects.equals(this.error, other.error);
    }


    public void addResponse(K response) {
        if (getResponses() == null) {
            setResponses(new HashSet<K>());
        }
        getResponses().add(response);
    }
}
