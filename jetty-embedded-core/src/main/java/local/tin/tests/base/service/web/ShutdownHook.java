package local.tin.tests.base.service.web;

import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public abstract class ShutdownHook extends Thread {

    protected abstract Logger getLogger();
    
    protected abstract void doShutdown();
        
    @Override
    public void run() {
        getLogger().info("Shutdown hook starts...");
        doShutdown();
        getLogger().info("Shutdown hook done...");
    }
   

    
}
