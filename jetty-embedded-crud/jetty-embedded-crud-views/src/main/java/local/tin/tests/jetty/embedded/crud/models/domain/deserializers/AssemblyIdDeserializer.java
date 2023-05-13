package local.tin.tests.jetty.embedded.crud.models.domain.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import local.tin.tests.jetty.embedded.crud.models.domain.compositeIds.AssemblyId;

/**
 *
 * @author benitodarder
 */
public class AssemblyIdDeserializer extends StdDeserializer<AssemblyId> {

    public AssemblyIdDeserializer() {
        super(AssemblyId.class);
    }

    public AssemblyIdDeserializer(Class<?> vc) {
        super(vc);
    }

    public AssemblyIdDeserializer(JavaType valueType) {
        super(valueType);
    }

    public AssemblyIdDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public AssemblyId deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jp.getCodec().readTree(jp);
        AssemblyId request = new AssemblyId();
        request.setComponentId(jsonNode.get("componentId").asInt());
        request.setProductId(jsonNode.get("productId").asInt());
        return request;
    }

}
