package local.tin.tests.model.domain.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import local.tin.tests.model.domain.abstracts.AbstractProduct;
import local.tin.tests.model.domain.deserializers.RequestDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({AbstractProduct.class})
@JsonDeserialize(using = RequestDeserializer.class)
public class Request extends AbstractRequest {

}
