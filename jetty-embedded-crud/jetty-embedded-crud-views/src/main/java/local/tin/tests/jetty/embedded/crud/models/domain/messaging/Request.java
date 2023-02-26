package local.tin.tests.jetty.embedded.crud.models.domain.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import local.tin.tests.jetty.embedded.crud.models.domain.abstracts.NamedEnableableByInteger;
import local.tin.tests.jetty.embedded.crud.models.domain.deserializers.RequestDeserializer;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Assembly;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Assembly.class, NamedEnableableByInteger.class})
@JsonDeserialize(using = RequestDeserializer.class)
public class Request extends local.tin.tests.jetty.embedded.core.models.domain.messaging.Request {

}
