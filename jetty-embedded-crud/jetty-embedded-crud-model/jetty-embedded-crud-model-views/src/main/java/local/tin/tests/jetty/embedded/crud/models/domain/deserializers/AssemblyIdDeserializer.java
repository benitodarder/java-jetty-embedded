package local.tin.tests.jetty.embedded.crud.models.domain.deserializers;

import java.io.IOException;
import local.tin.tests.jetty.embedded.crud.models.domain.compositeIds.AssemblyId;
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
public class AssemblyIdDeserializer extends StdDeserializer<AssemblyId> {

    public AssemblyIdDeserializer() {
        super(null);
    }

    public AssemblyIdDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AssemblyId deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = jp.getCodec().readTree(jp);
        AssemblyId request = new AssemblyId();
        request.setComponentId(jsonNode.get("componentId").getValueAsInt());
        request.setProductId(jsonNode.get("productId").getValueAsInt());
        return request;
    }

}
