package local.tin.tests.services.facades;

import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author benitodarder
 */
@Path("/ping")
public class Ping {
    
    
    @GET
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Hello being, it's " + new Date();
    }    
    
    @GET
    @Path("withParameter")
    @Produces(MediaType.TEXT_PLAIN)
    public String pingWithParamater(@QueryParam("parameter") String parameter) {
        return "Hello " + parameter + ", it's " + new Date();
    }    
    
    @POST
    @Path("withFormParameter")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetingsFromForm(@FormParam("parameter") String parameter) {
        return "Hello " + parameter + ", it's " + new Date();
    }   
    
    @POST
    @Path("withPayload")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String pingFromPayload(String parameter) {
        return "Hello " + parameter + ", it's " + new Date();
    }      
}    
    
   
