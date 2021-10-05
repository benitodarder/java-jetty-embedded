package local.tin.examples.jetty.embedded.logging.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import local.tin.examples.jetty.embedded.logging.service.UpperCaser;


/**
 *
 * @author benitodarder
 */
@Path("/upperCaser")
public class UpperCaserCtrl {
    
    private UpperCaser upperCaser = new UpperCaser();
    
    @POST
    @Path("transform")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response transformByPOST(String msg) {
        return Response.ok(upperCaser.transform(msg)).build();
    }    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response transformByGET(@QueryParam("parameter") String msg) {
       return Response.ok(upperCaser.transform(msg)).build();
    }     
}
