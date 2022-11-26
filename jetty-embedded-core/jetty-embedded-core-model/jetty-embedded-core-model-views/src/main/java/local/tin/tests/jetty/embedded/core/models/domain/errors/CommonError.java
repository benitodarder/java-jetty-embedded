package local.tin.tests.jetty.embedded.core.models.domain.errors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import local.tin.tests.jetty.embedded.core.models.domain.abstracts.View;
import local.tin.tests.jetty.embedded.core.models.domain.enums.ErrorCode;
import local.tin.tests.jetty.embedded.core.models.domain.interfaces.IView;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({View.class})
public class CommonError implements IView {
    
    private ErrorCode errorCode;
    private String errorMessage;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
   
}
