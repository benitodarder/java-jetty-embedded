package local.tin.tests.dao.impl;

import javax.persistence.EntityManagerFactory;
import local.tin.tests.model.data.product.Assembly;
import local.tin.tests.model.data.product.Component;
import local.tin.tests.model.data.product.Product;
import local.tin.tests.model.data.embeddables.AssemblyId;
import local.tin.tests.model.data.interfaces.IEmbeddable;
import local.tin.tests.model.domain.exceptions.DAOException;
import local.tin.tests.model.domain.interfaces.ICompositeId;

/**
 *
 * @author benito.darder
 */
public class AssemblyDAO extends AbstractEnableableDAO<local.tin.tests.model.domain.product.Assembly, Assembly> {

    public AssemblyDAO(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    protected Class<Assembly> getDAOClass() {
        return Assembly.class;
    }

    @Override
    protected AssemblyId getEmmbeddedId(ICompositeId domainObject) throws DAOException {
        AssemblyId embeddable = new AssemblyId();
        embeddable.setComponentId(((local.tin.tests.model.domain.compositeIds.AssemblyId) domainObject).getComponentId());
        embeddable.setProductId(((local.tin.tests.model.domain.compositeIds.AssemblyId) domainObject).getProductId());
        return embeddable;
    }

    @Override
    protected local.tin.tests.model.domain.compositeIds.AssemblyId getCompositedId(IEmbeddable dataObject) throws DAOException {
        local.tin.tests.model.domain.compositeIds.AssemblyId compositeId = new local.tin.tests.model.domain.compositeIds.AssemblyId();
        compositeId.setComponentId(((AssemblyId) dataObject).getComponentId());
        compositeId.setProductId(((AssemblyId) dataObject).getProductId());
        return compositeId;
    }

    @Override
    protected local.tin.tests.model.domain.product.Assembly getDomainObjectNewInstance() {
        return new local.tin.tests.model.domain.product.Assembly();
    }

    @Override
    protected Assembly getDataObjectNewInstance() {
        return new Assembly();
    }

    @Override
    protected local.tin.tests.model.domain.product.Assembly updateDomainObjectDepth0Fields(local.tin.tests.model.domain.product.Assembly domainObject, Assembly dataObject) throws DAOException {
        domainObject.setQuantity(dataObject.getQuantity());
        return domainObject;
    }

    @Override
    protected local.tin.tests.model.domain.product.Assembly updateDomainObjectDeeperFields(local.tin.tests.model.domain.product.Assembly domainObject, Assembly dataObject, int depth) throws DAOException {
        ComponentDAO componentDAO = (ComponentDAO) ProductDAOFactory.getInstance().getDAO(Component.class);
        domainObject.setComponent(componentDAO.getDomainObject(dataObject.getComponent(), DEFAULT_DEPTH_ENTITY));
        ProductDAO productDAO = (ProductDAO) ProductDAOFactory.getInstance().getDAO(Product.class);
        domainObject.setProduct(productDAO.getDomainObject(dataObject.getProduct(), DEFAULT_DEPTH_ENTITY));
        return domainObject;
    }

    @Override
    protected Assembly updateDataObjectDepth0Fields(local.tin.tests.model.domain.product.Assembly domainObject, Assembly dataObject) throws DAOException {
        dataObject.setQuantity(domainObject.getQuantity());
        return dataObject;
    }

    @Override
    protected Assembly updateDataObjectDeeperFields(local.tin.tests.model.domain.product.Assembly domainObject, Assembly dataObject, int depth) throws DAOException {
        return dataObject;
    }

}
