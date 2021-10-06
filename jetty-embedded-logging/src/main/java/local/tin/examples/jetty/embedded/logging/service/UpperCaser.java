package local.tin.examples.jetty.embedded.logging.service;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author benitodarder
 */
public class UpperCaser {

    public static final long MAX_DELAY = 1000l;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpperCaser.class);

    public String transform(String source) {
        Random rand = new Random();
        long delay = rand.nextInt((int) MAX_DELAY);
        LOGGER.info("Transform in, will apply delays by: " + delay);
        try {
            Thread.sleep(delay);
            LOGGER.debug("One delay of: " + delay + " applied");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        String result = source.toUpperCase();
        try {
            Thread.sleep(delay);
            LOGGER.debug("Another delay of: " + delay + " applied");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        LOGGER.debug("\n*********************************************\n Delay: " + delay  + "\nSource: " + source + "\nUpper cased: " + result + "\n*********************************************");
        try {
            Thread.sleep(delay);
            LOGGER.debug("Last delay of: " + delay + " applied");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        LOGGER.info("Transform out applied delays by: " + delay);
        return result;
    }

}
