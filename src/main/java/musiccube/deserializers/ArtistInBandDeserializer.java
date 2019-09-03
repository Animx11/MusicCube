package musiccube.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import musiccube.entities.Activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArtistInBandDeserializer extends StdDeserializer<Activity> {
    public ArtistInBandDeserializer () { this(null); }
    public ArtistInBandDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Activity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Activity activity = new Activity();
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        activity.setActive(!jsonNode.get("ended").asBoolean());
        if (jsonNode.hasNonNull("begin"))
            activity.setActivityStart(DateParser.parseDate(jsonNode.get("begin").asText()));
        if (jsonNode.hasNonNull("end")) {
            activity.setActivityEnd(DateParser.parseDate(jsonNode.get("end").asText()));
        }
        List<String> roles = new ArrayList<>();
        Iterator<JsonNode> jsonNodeIterator = jsonNode.get("attributes").elements();
        while (jsonNodeIterator.hasNext()) {
            String role = jsonNodeIterator.next().asText();
            roles.add(role);
        }
        activity.setRoles(roles.stream().toArray(String[]::new));

        return activity;
    }
}
