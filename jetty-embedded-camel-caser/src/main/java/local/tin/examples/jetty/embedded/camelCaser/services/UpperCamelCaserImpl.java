package local.tin.examples.jetty.embedded.camelCaser.services;

/**
 *
 * @author benitodarder
 */
public class UpperCamelCaserImpl extends AbstractCamelCaserImpl implements ICamelCaser {

    @Override
    public String transform(String source) {
        if (source.isEmpty()) {
            return source;
        }
        String result = transformToLowerCamelCase(source);
        return  result.substring(0, 1).toUpperCase() + result.substring(1);
    }

}
