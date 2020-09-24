package local.tin.tests.service.facades.crud;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.Response;
import local.tin.tests.model.domain.exceptions.ServiceException;
import local.tin.tests.model.domain.interfaces.IIdentifiable;
import local.tin.tests.service.crud.impl.AbstractCRUDService;
import local.tin.tests.service.facades.crud.intefaces.IAbstractCRUDResource;
import local.tin.tests.utils.json.JSONMapper;


/**
 *
 * @author benitodarder
 * 
 * @param <C>
 */
public abstract class AbstractCRUDResource<C extends IIdentifiable> implements IAbstractCRUDResource<C> {
    
    protected abstract Class<C> getCRUDClass();
    
    protected abstract AbstractCRUDService getCRUDService();
    

    @Override
    public Response retrieveAll() {
        try {
            List<C> units = getCRUDService().retrieveAll();
            return Response.status(Response.Status.OK).entity(JSONMapper.getInstance().getJSONFromObject(units)).build();
        } catch (ServiceException | IOException  ex) {
            return Response.status(Response.Status.CONFLICT).entity(ex.getLocalizedMessage()).build();
        }
    }    
    

    @Override
    public Response retrieve(C parameter) {
        try {
            C result = (C) getCRUDService().retrieve(parameter.getId());
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (ServiceException ex) {
            return Response.status(Response.Status.CONFLICT).entity(ex.getLocalizedMessage()).build();
        } 
    }  
    

    @Override
    public Response create(C parameter) {
        try {
            C result = (C) getCRUDService().create(parameter);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (ServiceException ex) {
            return Response.status(Response.Status.CONFLICT).entity(ex.getLocalizedMessage()).build();
        } 
    } 
    

    @Override
    public Response delete(C parameter) {
        try {
            getCRUDService().delete(parameter);
            return Response.status(Response.Status.OK).build();
        } catch (ServiceException ex) {
            return Response.status(Response.Status.CONFLICT).entity(ex.getLocalizedMessage()).build();
        } 
    }    
    

    @Override
    public Response update(C parameter) {
        try {
            C result = (C) getCRUDService().update(parameter);
            return Response.status(Response.Status.OK).entity(parameter).build();
        } catch (ServiceException ex) {
            return Response.status(Response.Status.CONFLICT).entity(ex.getLocalizedMessage()).build();
        } 
    }     
    
    
}
