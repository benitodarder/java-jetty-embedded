package local.tin.examples.jetty.embedded.helloWorld.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author benitodarder
 */
@Path("/helloWorld")
public class HelloWorld {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class.getCanonicalName());
    
    @POST
    @Path("greetings")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String greetings(String msg) {
        LOGGER.info("About to respond with message: {0}", msg);
        return "Hello " + msg;
    }

    @POST
    @Path("greetingsFromForm")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetingsFromForm(@FormParam("msg") String msg) {
        return "Hello " + msg;
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream inputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("msg") String msg) {

        StringBuilder textBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            int c = 0;
            textBuilder.append("Form parameter msg: ")
                    .append(msg)
                    .append(System.lineSeparator())
                    .append("File name: ")
                    .append(fileDetail.getFileName())
                    .append(System.lineSeparator())                    
                    .append("File content:")
                    .append(System.lineSeparator());
            while ((c = bufferedReader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException ioe) {
            textBuilder.append("Unexpected IOException: ")
                    .append(ioe.getMessage());
        }

        return Response.status(200).entity(textBuilder.toString()).build();

    }
}
