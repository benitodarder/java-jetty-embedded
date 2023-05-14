package local.tin.examples.jetty.embedded.camelCaser;

import java.util.logging.Logger;
import local.tin.examples.jetty.embedded.camelCaser.web.CamelCaserServer;

public class Application {

    public static final Logger LOGGER = Logger.getLogger(Application.class.getCanonicalName());

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
