package local.tin.tests.jetty.embedded.core.base.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataParam;

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

    @POST
    @Path("withMultiFormData")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String pingFromMultiFormData(@FormDataParam("parameter") String parameter, @FormDataParam("file") InputStream textFile) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(textFile, StandardCharsets.UTF_8);
        int charsRead;
        while ((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
            out.append(buffer, 0, charsRead);
        }
        return "Hello " + parameter + ", your file: " + out.toString() + ", it's " + new Date();
    }
    
    @POST
    @Path("withMultiFormDataSimple")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String pingFromMultiFormDataSimple(@FormDataParam("parameter") String parameter) throws IOException {

        return "Hello " + parameter + ", it's " + new Date();
    }    
}
