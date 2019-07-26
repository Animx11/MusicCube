package musiccube.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import musiccube.entities.Album;

import java.io.IOException;

public class AlbumDeserializer extends StdDeserializer<Album> {
    public AlbumDeserializer() { this(null); }
    public AlbumDeserializer(Class<?> vc) { super(vc); }

    @Override
    public Album deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException{
        Album album = new Album();
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        album.setAlbumName(jsonNode.get("title").asText());
        album.setReleaseDate(DateParser.parseDate(jsonNode.get("date").asText()));

        int lengthInMs = 0;
        //TODO zebrać i zsumować media.track.length

        //TODO album.setCoverArtLink();

        //TODO album.setCompany();
        return album;
    }
}
