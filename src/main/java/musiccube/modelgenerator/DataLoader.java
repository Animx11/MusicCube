package musiccube.modelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import musiccube.deserializers.ArtistDeserializer;
import musiccube.deserializers.ArtistInBandDeserializer;
import musiccube.deserializers.BandDeserializer;
import musiccube.deserializers.CountryDeserializer;
import musiccube.entities.*;
import musiccube.services.artist.ArtistService;
import musiccube.services.artistinband.ArtistInBandService;
import musiccube.services.band.BandService;
import musiccube.services.city.CityService;
import musiccube.services.country.CountryService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class DataLoader {
    private Logger logger = Logger.getLogger(DataLoader.class);
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;
    private ObjectMapper mapper = new ObjectMapper();

    private static final int ITERATIONS = 3;
    private static final String SAVED = " successfully saved.";
    private static final String BGN_AREA = "begin_area";
    private static final String LF_SPAN = "life-span";
    private static final String FMT = "?fmt=json";

    @Autowired
    private BandService bandService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistInBandService artistInBandService;

    public DataLoader() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Band.class, new BandDeserializer());
        module.addDeserializer(Country.class, new CountryDeserializer());
        module.addDeserializer(Artist.class, new ArtistDeserializer());
        module.addDeserializer(ArtistInBand.class, new ArtistInBandDeserializer());
        mapper.registerModule(module);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @PostConstruct
    public void generate() throws IOException, InterruptedException {
        for (int i=0;i<ITERATIONS;i++) {
            Band band = getBand(i);
            if (band != null)
                getLineup(band);
            else
                logger.warn("Band json lacking important fields, ignoring.");
        }
    }

    /*
    Checks, if fetched band data contains most important fields
     */
    private boolean checkBand(JSONObject obj) {
        return obj.has("area") &&
                obj.has("begin-area") &&
                obj.has("name") &&
                obj.has(LF_SPAN);

    }

    /*
    Gets one band, saves it and returns for further processing
     */
    private Band getBand(int offset) throws IOException, InterruptedException {
        try {
            Thread.sleep(600);
            response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/artist/?query=type:group&limit=1&offset="+Integer.toString(offset)+"&fmt=json", String.class);
            JSONObject obj = new JSONObject(response.getBody()).getJSONArray("artists").getJSONObject(0);
            if (checkBand(obj)) {
                Band band = mapper.readValue(obj.toString(), Band.class);
                band.setFormedIn(getCity(
                        obj.getJSONObject("area").getString("id"),
                        obj.getJSONObject("begin-area").getString("id")
                ));
                bandService.save(band);
                logger.info("Band "+obj.getString("name")+SAVED);
                return  band;
            }
        } catch (JSONException je) {
            logger.warn("Band is causing problems, ignoring");
        }
        return null;
    }

    /*
    Returns existing localization, or creates new one
     */
    private City getCity(String countryId, String areaId) throws IOException, InterruptedException {
        Thread.sleep(600);
        response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/area/"+countryId+FMT,String.class);
        JSONObject obj = new JSONObject(response.getBody());
        String code = obj.getJSONArray("iso-3166-1-codes").getString(0);
        Country country;
        if (countryService.existsByCode(code)) {
            country = countryService.getOneByCode(code);
            logger.info("Country "+country.getCountryName()+" already exists.");
        }
        else {
            country = mapper.readValue(obj.toString(), Country.class);
            countryService.save(country);
            logger.info("Country "+country.getCountryName()+SAVED);
        }

        Thread.sleep(600);
        response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/area/"+areaId+FMT,String.class);
        obj = new JSONObject(response.getBody());
        String name = obj.getString("name");
        City city;
        if(cityService.exists(name)) {
            city = cityService.getByCityName(name);
            logger.info("City/area "+city.getCityName()+" already exists.");
        }
        else {
            city = new City(name,country);
            cityService.save(city);
            logger.info("City/area "+city.getCityName()+SAVED);
        }
        return city;

    }

    /*
    Gets members of band, calls getArtist for each of them
     */
    private void getLineup(Band band) throws IOException, InterruptedException {
        logger.info("\n\nEntered getLineup\n\n");
        Thread.sleep(600);
        response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/artist/"+band.getMbId()+"?inc=artist-rels&fmt=json",String.class);
        JSONObject obj = new JSONObject(response.getBody());
        JSONArray relations = obj.getJSONArray("relations");
        for (int i = 0; i < relations.length(); i++) {
            obj = relations.getJSONObject(i);
            if (obj.getString("type").equals("member of band")) {
                Artist artist;
                if ( (artist = getArtist(obj.getJSONObject("artist").getString("id"))) != null) {
                    signToBand(artist, band, obj);
                }

            }
        }
    }

    /*

     */
    private Artist getArtist(String mbid) throws IOException, InterruptedException {
        logger.info("\n\nEntered getArtist\n\n");
        Artist artist;
        if (artistService.existsByMbId(mbid)) {
            return artistService.getByMbId(mbid);
        }
        else {
            try {
                Thread.sleep(600);
                response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/artist/" + mbid + FMT, String.class);
                JSONObject obj = new JSONObject(response.getBody());
                if (checkArtist(obj)) {
                    artist = mapper.readValue(obj.toString(), Artist.class);
                    artist.setOrigin(getCity(
                            obj.getJSONObject("area").getString("id"),
                            obj.getJSONObject(BGN_AREA).getString("id")
                    ));
                    artistService.save(artist);
                    logger.info("Artist " + artist.getStageName() + SAVED);
                    return artist;
                } else {
                    logger.warn("Artist json incomplete, ignoring.");
                }

            } catch (JSONException je) {
                logger.warn("Artist causing problems, ignoring",je);
            }
        }
        return null;
    }

    /*
    Checks if artist json includes required info
     */
    private boolean checkArtist(JSONObject obj) {
        return obj.has("name") &&
                obj.has(LF_SPAN) &&
                obj.has("area") &&
                obj.has(BGN_AREA) &&
                ! obj.isNull(BGN_AREA) &&
                ! obj.getJSONObject(LF_SPAN).isNull("begin");
    }

    /*
    Creates ArtistInBand object for given relation
     */
    private void signToBand(Artist artist, Band band, JSONObject relation) throws IOException {
        ArtistInBand artistInBand = mapper.readValue(relation.toString(),ArtistInBand.class);
        artistInBand.setArtist(artist);
        artistInBand.setBand(band);

        artistInBandService.save(artistInBand);
        logger.info((new StringBuilder())
                .append("Artist ")
                .append(artist.getStageName())
                .append(" added to band ")
                .append(band.getBandName())
                .append(".")
                .toString()
        );
    }

    /*

     */
    private void getAlbums(String mbid) throws InterruptedException {
        Thread.sleep(600);
        response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/release/?artist="+mbid+"&inc=artist-rels&fmt=json",String.class);
        JSONObject obj = new JSONObject(response.getBody());
    }
}
