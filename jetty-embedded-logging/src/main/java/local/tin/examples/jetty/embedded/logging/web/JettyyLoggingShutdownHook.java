package local.tin.examples.jetty.embedded.logging.web;

import org.slf4j.Logger;
import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import org.slf4j.LoggerFactory;

/**
 *
 * @author benitodarder
 */
public class JettyyLoggingShutdownHook extends ShutdownHook {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JettyyLoggingShutdownHook.class.getCanonicalName());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected void doShutdown() {
        LOGGER.info("Shutingdown...");
    }
    
}
