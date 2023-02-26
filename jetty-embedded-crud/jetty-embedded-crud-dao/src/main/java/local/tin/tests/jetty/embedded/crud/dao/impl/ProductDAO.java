package local.tin.tests.jetty.embedded.crud.dao.impl;

import javax.persistence.EntityManagerFactory;
import local.tin.tests.jetty.embedded.core.dao.impl.EnableableDAO;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.DAOException;
import local.tin.tests.jetty.embedded.crud.dao.model.product.Assembly;
import local.tin.tests.jetty.embedded.crud.dao.model.product.Product;

/**
 *
 * @author benito.darder
 */
public class ProductDAO extends EnableableDAO<local.tin.tests.jetty.embedded.crud.models.domain.product.Product, Product> {

    public ProductDAO(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    protected Class<Product> getDAOClass() {
        return Product.class;
    }

    @Override
    protected local.tin.tests.jetty.embedded.crud.models.domain.product.Product getDomainObjectNewInstance() {
        return new local.tin.tests.jetty.embedded.crud.models.domain.product.Product();
    }

    @Override
    protected Product getDataObjectNewInstance() {
        return new Product();
    }

    @Override
    protected local.tin.tests.jetty.embedded.crud.models.domain.product.Product updateDomainObjectDepth0Fields(local.tin.tests.jetty.embedded.crud.models.domain.product.Product domainObject, Product dataObject) throws DAOException {
        domainObject.setDescription(dataObject.getDescription());
        domainObject.setName(dataObject.getName());
        return domainObject;
    }

    @Override
    protected local.tin.tests.jetty.embedded.crud.models.domain.product.Product updateDomainObjectDeeperFields(local.tin.tests.jetty.embedded.crud.models.domain.product.Product domainObject, Product dataObject, int depth) throws DAOException {
        AssemblyDAO assemblyDAO = (AssemblyDAO) ProductDAOFactory.getInstance().getDAO(Assembly.class);
        for (Assembly current : dataObject.getAssemblies()) {
            domainObject.getAssemblies().add(assemblyDAO.getDomainObject(current, depth));
        }
        return domainObject;
    }

    @Override
    protected Product updateDataObjectDepth0Fields(local.tin.tests.jetty.embedded.crud.models.domain.product.Product domainObject, Product dataObject) throws DAOException {
        dataObject.setName(domainObject.getName());
        dataObject.setDescription(domainObject.getDescription());
        return dataObject;
    }

    @Override
    protected Product updateDataObjectDeeperFields(local.tin.tests.jetty.embedded.crud.models.domain.product.Product domainObject, Product dataObject, int depth) throws DAOException {
        AssemblyDAO assemblyDAO = (AssemblyDAO) ProductDAOFactory.getInstance().getDAO(Assembly.class);
        for (local.tin.tests.jetty.embedded.crud.models.domain.product.Assembly current : domainObject.getAssemblies()) {
            dataObject.getAssemblies().add(assemblyDAO.getDataObject(current, depth));
        }        
        return dataObject;
    }

}
