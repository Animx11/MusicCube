package musiccube.deserializers;

import musiccube.entities.Artist;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ArtistDeserializer extends StdDeserializer<Artist> {
    public ArtistDeserializer() { this(null); }
    public ArtistDeserializer(Class<?> vc) { super(vc); }

    @Override
    public Artist deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Artist artist = new Artist();
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        artist.setStageName(jsonNode.get("name").asText());
        artist.setMbId(jsonNode.get("id").asText());

        jsonNode = jsonNode.get("life-span");
        artist.setBirthDate(DateParser.parseDate(jsonNode.get("begin").asText()));
        if (jsonNode.has("end") && !jsonNode.get("end").isNull()) {
            artist.setDeathDate(DateParser.parseDate(jsonNode.get("end").asText()));
        }

        return artist;
    }

}
