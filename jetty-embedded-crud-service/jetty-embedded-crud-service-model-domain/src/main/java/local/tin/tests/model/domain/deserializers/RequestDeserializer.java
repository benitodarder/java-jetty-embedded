package local.tin.tests.model.domain.deserializers;

import java.io.IOException;
import local.tin.tests.model.domain.messaging.Request;
import local.tin.tests.model.domain.product.Assembly;
import local.tin.tests.model.domain.product.Component;
import local.tin.tests.model.domain.product.Product;
import local.tin.tests.model.domain.product.Unit;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.StdDeserializer;

/**
 *
 * @author benitodarder
 */
public class RequestDeserializer extends StdDeserializer<Request> {

    public RequestDeserializer() {
        super(null);
    }

    public RequestDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Request deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = jp.getCodec().readTree(jp);
        Request request = new Request();
        if (jsonNode.get("assembly") != null) {
            request.setItem(mapper.readValue(jsonNode.get("assembly").toString(), Assembly.class));
        } else if (jsonNode.get("component") != null) {
            request.setItem(mapper.readValue(jsonNode.get("component").toString(), Component.class));
        } else if (jsonNode.get("product") != null) {
            request.setItem(mapper.readValue(jsonNode.get("product").toString(), Product.class));
        } else if (jsonNode.get("unit") != null) {
            request.setItem(mapper.readValue(jsonNode.get("unit").toString(), Unit.class));
        }        
        return request;
    }

}
