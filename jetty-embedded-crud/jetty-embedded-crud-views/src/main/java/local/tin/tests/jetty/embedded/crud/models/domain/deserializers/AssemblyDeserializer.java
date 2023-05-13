package local.tin.tests.jetty.embedded.crud.models.domain.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import local.tin.tests.jetty.embedded.crud.models.domain.compositeIds.AssemblyId;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Assembly;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Component;
import local.tin.tests.jetty.embedded.crud.models.domain.product.Product;

/**
 *
 * @author benitodarder
 */
public class AssemblyDeserializer extends StdDeserializer<Assembly> {

    public AssemblyDeserializer() {
        super(Assembly.class);
    }

    public AssemblyDeserializer(Class<?> vc) {
        super(vc);
    }

    public AssemblyDeserializer(JavaType valueType) {
        super(valueType);
    }

    public AssemblyDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Assembly deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = jp.getCodec().readTree(jp);
        AssemblyId assemblyId = new AssemblyId();
        assemblyId.setComponentId(jsonNode.get("id").get("componentId").asInt());
        assemblyId.setProductId(jsonNode.get("id").get("productId").asInt());
        Assembly assembly = new Assembly();
        assembly.setId(assemblyId);
        if (jsonNode.get("component") != null) {
            assembly.setComponent(mapper.readValue(jsonNode.get("component").toString(), Component.class));
        }
        if (jsonNode.get("product") != null) {
            assembly.setProduct(mapper.readValue(jsonNode.get("product").toString(), Product.class));
        }
        if (jsonNode.get("quantity") != null) {
            assembly.setQuantity(jsonNode.get("quantity").asDouble());
        }
        return assembly;
    }

}
