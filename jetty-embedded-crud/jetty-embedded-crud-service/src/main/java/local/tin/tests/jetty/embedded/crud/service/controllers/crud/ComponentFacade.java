package local.tin.tests.jetty.embedded.crud.service.controllers.crud;

import javax.ws.rs.Path;
import local.tin.tests.jetty.embedded.core.models.domain.exceptions.ServiceException;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Component;
import local.tin.tests.jetty.embedded.crud.service.crud.impl.AbstractCRUDService;
import local.tin.tests.jetty.embedded.crud.service.crud.impl.ComponentCRUD;
import local.tin.tests.jetty.embedded.crud.service.crud.impl.ProductCRUDServiceFactory;


/**
 *
 * @author benitodarder
 */
@Path("/crud/component")
public class ComponentFacade extends AbstractCRUDResource<Component> {
    
    private final ComponentCRUD crud;

    public ComponentFacade() throws ServiceException {
        crud = (ComponentCRUD) ProductCRUDServiceFactory.getInstance().getCRUD(Component.class);
    }

    @Override
    protected Class<Component> getCRUDClass() {
        return Component.class;
    }

    @Override
    protected AbstractCRUDService getCRUDService() {
        return crud;
    }
    
}
