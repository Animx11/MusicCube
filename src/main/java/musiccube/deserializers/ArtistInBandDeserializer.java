package musiccube.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import musiccube.entities.ArtistActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArtistInBandDeserializer extends StdDeserializer<ArtistActivity> {
    public ArtistInBandDeserializer () { this(null); }
    public ArtistInBandDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ArtistActivity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ArtistActivity artistActivity = new ArtistActivity();
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        artistActivity.setActive(!jsonNode.get("ended").asBoolean());
        if (jsonNode.hasNonNull("begin"))
            artistActivity.setActivityStart(DateParser.parseDate(jsonNode.get("begin").asText()));
        if (jsonNode.hasNonNull("end")) {
            artistActivity.setActivityEnd(DateParser.parseDate(jsonNode.get("end").asText()));
        }
        List<String> roles = new ArrayList<>();
        Iterator<JsonNode> jsonNodeIterator = jsonNode.get("attributes").elements();
        while (jsonNodeIterator.hasNext()) {
            String role = jsonNodeIterator.next().asText();
            roles.add(role);
        }
        artistActivity.setRoles(roles.stream().toArray(String[]::new));

        return artistActivity;
    }
}
