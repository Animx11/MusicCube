package musiccube.deserializers;

import musiccube.entities.Band;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class BandDeserializer extends StdDeserializer<Band> {
    public BandDeserializer() { this(null); }
    public BandDeserializer(Class<?> vc) { super(vc); }

    @Override
    public Band deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Band band = new Band();
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        band.setBandName(jsonNode.get("name").asText());
        band.setMbId(jsonNode.get("id").asText());
        band.setCreationDate(DateParser.parseDate(jsonNode.get("life-span").get("begin").asText()));

        return band;
    }
}
