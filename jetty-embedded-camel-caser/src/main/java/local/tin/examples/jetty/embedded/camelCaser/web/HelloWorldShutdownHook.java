package local.tin.examples.jetty.embedded.camelCaser.web;

import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class HelloWorldShutdownHook extends ShutdownHook {
    
    private static final Logger LOGGER = Logger.getLogger(HelloWorldShutdownHook.class);

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected void doShutdown() {
        LOGGER.info("Shutingdown...");
    }
    
}
