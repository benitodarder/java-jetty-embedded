package local.tin.examples.jetty.embedded;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import local.tin.examples.jetty.embedded.facades.HelloWorld;
import local.tin.examples.jetty.embedded.facades.Ping;
import local.tin.examples.jetty.embedded.web.HelloWorldServer;
import local.tin.examples.jetty.embedded.web.HelloWorldShutdownHook;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class App {

    public static final Logger LOGGER = Logger.getLogger(App.class);
    public static final String PATH_SPEC = "/*";    
    public static final String SERVER_APPLICATION_PATH = "/path";
    public static final int SERVER_HTTP_PORT = 8080;    
    
 
    public static void main(String[] args) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(HelloWorldServer.SERVER_PATH_PARAMETER, SERVER_APPLICATION_PATH);
        parameters.put(HelloWorldServer.SERVER_HTTP_PORT_PARAMETER, SERVER_HTTP_PORT);
        parameters.put(HelloWorldServer.PATH_SPEC, PATH_SPEC);
        parameters.put(HelloWorldServer.SERVER_PROVIDER_CLASSES, Ping.class.getCanonicalName() + ";" + HelloWorld.class.getCanonicalName() + ";" + MultiPartFeature.class.getCanonicalName());      
        parameters.put(HelloWorldServer.SERVER_SHUTOWN_HOOK, new HelloWorldShutdownHook());
        
        HelloWorldServer productServer = new HelloWorldServer(parameters);

        showInitParameters(productServer);
        
        try {
            productServer.run();
        } finally {
            productServer.destroy();
        }
        
    }

    public static void showInitParameters(HelloWorldServer productServer) throws ClassNotFoundException, SecurityException {
        Map<String, String> initParameters = productServer.getInitParameters();
        
        LOGGER.info("Init parameters:");
        for (Map.Entry<String, String> entry : initParameters.entrySet()) {
            LOGGER.info(entry.getKey() + " : " + entry.getValue());
            for(String currentClass : entry.getValue().split(";")) {
                Class klazz = Class.forName(currentClass);
                LOGGER.info("For class: " + currentClass + " we have the methods:");
                for (Method method : klazz.getDeclaredMethods()) {
                    LOGGER.info("\t" + method.getName() + ":");
                    LOGGER.info("\t\tAnnotations:");
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        LOGGER.info("\t\t\t" + annotation.toString());
                    }
                    LOGGER.info("\t\tParameters by getParameterTypes():");
                    for (Class parameterType : method.getParameterTypes()) {
                        LOGGER.info("\t\t\t" + parameterType.getCanonicalName());
                    }
                    LOGGER.info("\t\tParameters by getParameters():");
                    for (Parameter parameterType : method.getParameters()) {
                        LOGGER.info("\t\t\t" + parameterType.getName() + ", " + parameterType.getType().getName() + ", " + parameterType.isNamePresent());
                    }                    
                }
            }

        }
    }
}
