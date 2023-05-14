package local.tin.examples.jetty.embedded.logging;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.tin.examples.jetty.embedded.logging.web.JettyLoggingServer;

public class Application {

    public static final Logger LOGGER = Logger.getLogger(Application.class.getCanonicalName());

    public static void main(String[] args) throws Exception {

        JettyLoggingServer productServer = new JettyLoggingServer(new Configuration());
        Thread thread = new Thread(productServer);
        showInitParameters(productServer);
        try {
            thread.start();
            while (!Thread.currentThread().isInterrupted()) {}
        } finally {
            productServer.stopServer();
            productServer.destroyServer();
        }

    }

    public static void showInitParameters(JettyLoggingServer productServer) throws ClassNotFoundException, SecurityException {
        Map<String, String> initParameters = productServer.getInitParameters();

        LOGGER.info("Init parameters:");
        for (Map.Entry<String, String> entry : initParameters.entrySet()) {
            LOGGER.log(Level.INFO, "{0} : {1}", new Object[]{entry.getKey(), entry.getValue()});
            for (String currentClass : entry.getValue().split(";")) {
                Class klazz = Class.forName(currentClass);
                LOGGER.log(Level.INFO, "For class: {0} we have the methods:", currentClass);
                for (Method method : klazz.getDeclaredMethods()) {
                    LOGGER.log(Level.INFO, "\t{0}:", method.getName());
                    LOGGER.info("\t\tAnnotations:");
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        LOGGER.log(Level.INFO, "\t\t\t{0}", annotation.toString());
                    }
                    LOGGER.info("\t\tParameters by getParameterTypes():");
                    for (Class parameterType : method.getParameterTypes()) {
                        LOGGER.log(Level.INFO, "\t\t\t{0}", parameterType.getCanonicalName());
                    }
                    LOGGER.info("\t\tParameters by getParameters():");
                    for (Parameter parameterType : method.getParameters()) {
                        LOGGER.log(Level.INFO, "\t\t\t{0}, {1}, {2}", new Object[]{parameterType.getName(), parameterType.getType().getName(), parameterType.isNamePresent()});
                    }
                }
            }

        }
    }
}
