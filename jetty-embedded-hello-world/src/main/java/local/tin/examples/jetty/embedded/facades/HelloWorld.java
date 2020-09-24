package local.tin.examples.jetty.embedded.facades;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author benitodarder
 */
@Path("/helloWorld")
public class HelloWorld {
    
    @POST
    @Path("greetings")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String greetings(String msg) {
        return "Hello " + msg;
    }    
    
    @POST
    @Path("greetingsFromForm")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetingsFromForm(@FormParam("msg") String msg) {
        return "Hello " + msg;
    }     
}
