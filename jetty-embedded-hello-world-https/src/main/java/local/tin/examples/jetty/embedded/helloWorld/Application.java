package local.tin.examples.jetty.embedded.helloWorld;

import java.util.logging.Logger;
import local.tin.examples.jetty.embedded.helloWorld.web.HelloWorldSSLServer;

public class Application {

    public static final Logger LOGGER = Logger.getLogger(Application.class.getCanonicalName());

    public static void main(String[] args) throws Exception {

        HelloWorldSSLServer productServer = new HelloWorldSSLServer(new Configuration());
        Thread thread = new Thread(productServer);

        try {
            thread.start();
            while (!Thread.currentThread().isInterrupted()) {}
        } finally {
            productServer.stopServer();
            productServer.destroyServer();
        }

    }

}
