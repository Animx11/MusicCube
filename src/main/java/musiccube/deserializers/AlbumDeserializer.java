package musiccube.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import musiccube.entities.Album;

import java.io.IOException;
import java.util.stream.StreamSupport;

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

        int lengthInMs = StreamSupport.stream(jsonNode.get("media").spliterator(), false)
                                .mapToInt(medium -> StreamSupport.stream(medium.get("tracks").spliterator(),false)
                                        .mapToInt(track -> track.get("length").asInt())
                                        .sum())
                                .sum();
        album.setAlbumLengthSeconds(lengthInMs/1000);

        if (jsonNode.get("cover-art-archive").get("front").asBoolean()) {
            album.setCoverArtLink("https://coverartarchive.org/release/"+jsonNode.get("id").asText()+"/front");
        }

        int trackCount = StreamSupport.stream(jsonNode.get("media").spliterator(),false)
                .mapToInt(medium -> medium.get("track-count").asInt())
                .sum();
        album.setTrackCount(trackCount);

        album.setCompany(jsonNode.get("label-info").get(0).get("name").asText());
        return album;
    }
}
