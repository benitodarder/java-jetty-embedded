package local.tin.tests.model.domain.abstracts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import local.tin.tests.model.domain.product.Component;
import local.tin.tests.model.domain.product.Product;
import local.tin.tests.model.domain.product.Unit;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Component.class, Product.class, Unit.class})
public abstract class AbstractNamedProduct extends AbstractNamed {
    
}
