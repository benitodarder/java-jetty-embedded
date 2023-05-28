package local.tin.examples.jetty.embedded.camelCaser.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;



/**
 *
 * @author benitodarder
 */
public class HelloWorldShutdownHook extends ShutdownHook {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldShutdownHook.class.getCanonicalName());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected void doShutdown() {
        LOGGER.info("Shutingdown...");
    }
    
}
