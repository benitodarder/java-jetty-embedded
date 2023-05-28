package local.tin.examples.jetty.embedded.helloWorld;

import org.slf4j.Logger;
import local.tin.examples.jetty.embedded.helloWorld.web.HelloWorldSSLServer;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class.getCanonicalName());

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
