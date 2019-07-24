package musiccube.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import musiccube.entities.Country;

import java.io.IOException;

public class CountryDeserializer extends StdDeserializer<Country> {
    public CountryDeserializer() { this(null); }
    public CountryDeserializer(Class<?> vc) { super(vc); }

    @Override
    public Country deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Country country = new Country();
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        country.setCountryName(jsonNode.get("name").asText());
        country.setCode(jsonNode.get("iso-3166-1-codes").get(0).asText());

        return country;
    }
}
