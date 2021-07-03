package local.tin.tests.jetty.embedded.core.base.web;

import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public abstract class ShutdownHook extends Thread {

    protected abstract Logger getLogger();
    
    /**
     * Performs all required shutdown tasks... Gracefully disconnect from 
     * databases, closes files...
     */
    protected abstract void doShutdown();
        
    @Override
    public void run() {
        getLogger().info("Shutdown hook starts...");
        doShutdown();
        getLogger().info("Shutdown hook done...");
    }
   

    
}
