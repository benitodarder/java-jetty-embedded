package local.tin.examples.jetty.embedded.logging.web;

import java.util.logging.Logger;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;

/**
 *
 * @author benitodarder
 */
public class JettyyLoggingShutdownHook extends ShutdownHook {
    
    private static final Logger LOGGER = Logger.getLogger(JettyyLoggingShutdownHook.class.getCanonicalName());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected void doShutdown() {
        LOGGER.info("Shutingdown...");
    }
    
}
