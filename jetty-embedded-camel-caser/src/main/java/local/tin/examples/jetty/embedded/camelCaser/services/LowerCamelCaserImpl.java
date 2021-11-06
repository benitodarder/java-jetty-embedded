package local.tin.examples.jetty.embedded.camelCaser.services;

/**
 *
 * @author benitodarder
 */
public class LowerCamelCaserImpl extends AbstractCamelCaserImpl implements ICamelCaser {


    @Override
    public String transform(String source) {
        return transformToLowerCamelCase(source);
    }

}
