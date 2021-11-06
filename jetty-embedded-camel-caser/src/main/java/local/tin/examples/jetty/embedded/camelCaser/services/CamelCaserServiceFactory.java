package local.tin.examples.jetty.embedded.camelCaser.services;

/**
 *
 * @author benitodarder
 */
public class CamelCaserServiceFactory {

    private CamelCaserServiceFactory() {
    }

    public static CamelCaserServiceFactory getInstance() {
        return CamelCaserServiceFactoryHolder.INSTANCE;
    }

    private static class CamelCaserServiceFactoryHolder {
        private static final CamelCaserServiceFactory INSTANCE = new CamelCaserServiceFactory();
    }
    
    public UpperCamelCaserImpl getUpperCamelCaserImpl() {
        return new UpperCamelCaserImpl();
    }
    
    public LowerCamelCaserImpl getLowerCamelCaserImpl() {
        return new LowerCamelCaserImpl();
    }
 }
