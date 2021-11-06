package local.tin.examples.jetty.embedded.camelCaser;

import local.tin.examples.jetty.embedded.camelCaser.web.CamelCaserServer;
import org.apache.log4j.Logger;

public class Application {

    public static final Logger LOGGER = Logger.getLogger(Application.class);

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
