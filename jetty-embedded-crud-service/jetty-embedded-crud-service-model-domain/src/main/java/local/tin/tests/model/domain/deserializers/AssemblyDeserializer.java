package local.tin.tests.model.domain.deserializers;

import java.io.IOException;
import local.tin.tests.model.domain.compositeIds.AssemblyId;
import local.tin.tests.model.domain.product.Assembly;
import local.tin.tests.model.domain.product.Component;
import local.tin.tests.model.domain.product.Product;
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
public class AssemblyDeserializer extends StdDeserializer<Assembly> {

    public AssemblyDeserializer() {
        super(null);
    }

    public AssemblyDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Assembly deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = jp.getCodec().readTree(jp);
        AssemblyId assemblyId = new AssemblyId();
        assemblyId.setComponentId(jsonNode.get("id").get("componentId").getValueAsInt());
        assemblyId.setProductId(jsonNode.get("id").get("productId").getValueAsInt());
        Assembly assembly = new Assembly();
        assembly.setId(assemblyId);
        if (jsonNode.get("component") != null) {
            assembly.setComponent(mapper.readValue(jsonNode.get("component").toString(), Component.class));
        }
        if (jsonNode.get("product") != null) {
            assembly.setProduct(mapper.readValue(jsonNode.get("product").toString(), Product.class));
        }
        if (jsonNode.get("quantity") != null) {
            assembly.setQuantity(jsonNode.get("quantity").getValueAsDouble());
        }
        return assembly;
    }

}
