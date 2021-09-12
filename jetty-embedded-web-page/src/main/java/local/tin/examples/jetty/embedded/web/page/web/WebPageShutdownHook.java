package local.tin.examples.jetty.embedded.web.page.web;

import local.tin.tests.jetty.embedded.core.base.web.ShutdownHook;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class WebPageShutdownHook extends ShutdownHook {
    
    private static final Logger LOGGER = Logger.getLogger(WebPageShutdownHook.class);

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected void doShutdown() {
        LOGGER.info("Shutingdown...");
    }
    
}
