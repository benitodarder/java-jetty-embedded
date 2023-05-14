package local.tin.examples.jetty.embedded.logging.service;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benitodarder
 */
public class UpperCaser {

    public static final long MAX_DELAY = 1000l;
    private static final Logger LOGGER = Logger.getLogger(UpperCaser.class.getCanonicalName());

    public String transform(String source) {
        Random rand = new Random();
        long delay = rand.nextInt((int) MAX_DELAY);
        LOGGER.log(Level.INFO, "Transform in, will apply delays by: {0}", delay);
        try {
            Thread.sleep(delay);
            LOGGER.log(Level.FINE, "One delay of: {0} applied", delay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        String result = source.toUpperCase();
        try {
            Thread.sleep(delay);
            LOGGER.log(Level.SEVERE, "Another delay of: {0} applied", delay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        LOGGER.log(Level.SEVERE, "\n*********************************************\n Delay: {0}\nSource: {1}\nUpper cased: {2}\n*********************************************", new Object[]{delay, source, result});
        try {
            Thread.sleep(delay);
            LOGGER.log(Level.SEVERE, "Last delay of: {0} applied", delay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        LOGGER.log(Level.INFO, "Transform out applied delays by: {0}", delay);
        return result;
    }

}
