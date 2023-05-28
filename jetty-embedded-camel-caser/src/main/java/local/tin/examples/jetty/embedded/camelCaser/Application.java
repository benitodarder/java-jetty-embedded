package local.tin.examples.jetty.embedded.camelCaser;

import org.slf4j.Logger;
import local.tin.examples.jetty.embedded.camelCaser.web.CamelCaserServer;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class.getCanonicalName());

    public static void main(String[] args) throws Exception {

        CamelCaserServer productServer = new CamelCaserServer(new Configuration());
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
