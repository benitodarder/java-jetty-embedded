package local.tin.examples.jetty.embedded.camelCaser.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import local.tin.examples.jetty.embedded.camelCaser.services.CamelCaserServiceFactory;

/**
 *
 * @author benitodarder
 */
@Path("/camelCaser")
public class CamelCaserCtrl {

    @POST
    @Path("upperCamelCaser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String upperCamelCaser(String msg) {
        return CamelCaserServiceFactory.getInstance().getUpperCamelCaserImpl().transform(msg);
    }

    @POST
    @Path("lowerCamelCaser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String lowerCamelCaser(String msg) {
        return CamelCaserServiceFactory.getInstance().getLowerCamelCaserImpl().transform(msg);
    }
}
