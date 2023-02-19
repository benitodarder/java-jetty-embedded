package local.tin.tests.jetty.embedded.crud.models.domain.abstracts;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import local.tin.tests.jetty.embedded.core.models.domain.abstracts.Enableable;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Assembly;

/**
 *
 * @author benito.darder
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Assembly.class})
public abstract class Entity<K extends Serializable> extends Enableable<K> {
    
   
    
}
