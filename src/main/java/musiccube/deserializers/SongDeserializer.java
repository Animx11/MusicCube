package musiccube.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import musiccube.entities.Song;

import java.io.IOException;

public class SongDeserializer extends StdDeserializer<Song> {
    public SongDeserializer() { this(null); }
    public SongDeserializer(Class<?> vc) { super(vc); }

    @Override
    public Song deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Song song = new Song();
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        song.setSongName(jsonNode.get("title").asText());
        song.setSongLengthSeconds(jsonNode.get("length").asInt() / 1000);

        return song;
    }
}
