package local.tin.examples.jetty.embedded.logging.service;

import java.util.Random;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author benitodarder
 */
public class UpperCaser {

    public static final long MAX_DELAY = 1000l;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpperCaser.class.getCanonicalName());

    public String transform(String source) {
        Random rand = new Random();
        long delay = rand.nextInt((int) MAX_DELAY);
        LOGGER.info("Transform in, will apply delays by: {0}", delay);
        try {
            Thread.sleep(delay);
            LOGGER.debug("One delay of: {0} applied", delay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        String result = source.toUpperCase();
        try {
            Thread.sleep(delay);
            LOGGER.error("Another delay of: {0} applied", delay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        LOGGER.error("\n*********************************************\n Delay: {0}\nSource: {1}\nUpper cased: {2}\n*********************************************", new Object[]{delay, source, result});
        try {
            Thread.sleep(delay);
            LOGGER.error("Last delay of: {0} applied", delay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        LOGGER.info("Transform out applied delays by: {0}", delay);
        return result;
    }

}
