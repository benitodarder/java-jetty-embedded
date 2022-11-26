package local.tin.tests.jetty.embedded.crud.models.domain.abstracts;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import local.tin.tests.jetty.embedded.core.models.domain.abstracts.Named;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Component;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Product;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Unit;


/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Component.class, Product.class, Unit.class})
public abstract class AbstractNamedProduct<K extends Serializable> extends Named<K> {
    
}
