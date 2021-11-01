package local.tin.examples.jetty.embedded.camelCaser.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import local.tin.examples.jetty.embedded.camelCaser.services.UpperCamelCaserImpl;

/**
 *
 * @author benitodarder
 */
@Path("/camelCaser")
public class CamelCaserCtrl {

    private final UpperCamelCaserImpl upperCamelCaserImpl;

    public CamelCaserCtrl() {
        upperCamelCaserImpl = new UpperCamelCaserImpl();
    }

    @POST
    @Path("upperCamelCaser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String upperCamelCaser(String msg) {
        return upperCamelCaserImpl.transform(msg);
    }

}
