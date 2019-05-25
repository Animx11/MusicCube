package MusicCube.takingData;

import MusicCube.entities.Album;
import MusicCube.entities.Country;
import MusicCube.entities.Genre;
import MusicCube.entities.Instrument;
import MusicCube.services.album.AlbumService;
import MusicCube.services.country.CountryService;
import MusicCube.services.genre.GenreService;
import MusicCube.services.instrument.InstrumentService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RestClientForMusicBrainzApi {

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private AlbumService albumService;

    private final String URL = "https://musicbrainz.org/ws/2/";

    private final String INSTRUMENTS = "instrument/?query=*";
    //private final String[] INSTRUMENT_TYPE = {"\"Wind instrument\"", "\"String instrument\"", "\"Percussion instrumen\"", "\"Electronic instrument\"", "\"Other instrument\""};
    private final String ALBUMS = "release/?query=*";

    private final int LIMIT_INT = 100;
    private final String LIMIT_URL = "&limit=100";
    private final String JSON_TYPE_URL = "&fmt=json";
    private final String OFFSET = "&offset=";

    private final String GENRES_FILE = ".\\src\\main\\java\\MusicCube\\takingData\\genres.txt";
    private final String COUNTRIES_FILE = ".\\src\\main\\java\\MusicCube\\takingData\\countries.txt";
    
    private RestTemplate restTemplate = new RestTemplate();
    private JSONParser jsonParser = new JSONParser();
    private List<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Void> takeData(){

        initDataPatterns();
        //takeCountries();
        //takeGenres();

        try {
            takeInstruments();
            takeAlbums();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void takeInstruments() throws InterruptedException{
        String instrumentName;
        String type;
        int i = 0;

        boolean notEmpty = true;

        while (notEmpty) {
            Thread.sleep(750);
            String offsetNum = Integer.toString(i);
            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + INSTRUMENTS + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);
            try{
                Object responseJsonBody = jsonParser.parse(takeResponseFromApi.getBody());
                JSONObject responseJsonBodyToJsonObj = (JSONObject) responseJsonBody;
                JSONArray takingInstrumentJsonArray = (JSONArray) responseJsonBodyToJsonObj.get("instruments");
                Object[] converteToObjectArray = takingInstrumentJsonArray.toArray();
                JSONObject jsonObjectInstrument;

                if(converteToObjectArray.length == 0){
                    notEmpty = false;
                }

                for(int j = 0; j < converteToObjectArray.length; j++){
                    jsonObjectInstrument = (JSONObject) converteToObjectArray[j];
                    instrumentName = (String) jsonObjectInstrument.get("name");
                    type = (String) jsonObjectInstrument.get("type");
                    if(!instrumentService.existsByInstrumentName(instrumentName)) {
                        Instrument instrument = new Instrument(instrumentName, type);
                        instrumentService.save(instrument);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            i++;
        }
    }

    private void takeAlbums() throws InterruptedException{
        String albumName = "";
        Date releaseDate = new Date();
        String company = "";
        int i = 0;

        //DateFormat dateFormat = new SimpleDateFormat("yyyy");

        boolean notEmpty = true;

        while (notEmpty) {
            Thread.sleep(750);
            String offsetNum = Integer.toString(i);
            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + ALBUMS + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);

            try{
                Object responseJsonBody = jsonParser.parse(takeResponseFromApi.getBody());
                JSONObject responseJsonBodyToJsonObj = (JSONObject) responseJsonBody;
                JSONArray takingReleasesJsonArray = (JSONArray) responseJsonBodyToJsonObj.get("releases");
                Object[] converteToObjectArray = takingReleasesJsonArray.toArray();
                JSONObject jsonObjectReleases;

                if(converteToObjectArray.length == 0){
                    notEmpty = false;
                }

                for(int j = 0; j < converteToObjectArray.length; j++){
                    jsonObjectReleases = (JSONObject) converteToObjectArray[j];
                    albumName = (String) jsonObjectReleases.get("title");

                    for (SimpleDateFormat pattern : knownPatterns) {
                        try {
                            releaseDate = pattern.parse((String) jsonObjectReleases.get("date"));
                        }catch (ParseException e){}
                    }

                    JSONArray takingLabelInfoJsonArray = (JSONArray) jsonObjectReleases.get("label-info");
                    if(takingLabelInfoJsonArray != null) {

                        Object[] convertLabelInfoJsonArrayToObjectArray = takingLabelInfoJsonArray.toArray();
                        JSONObject jsonObjectLabelInfo = (JSONObject) convertLabelInfoJsonArrayToObjectArray[0];

                        if(jsonObjectLabelInfo.get("label")!= null) {
                            JSONObject takingLabelJsonObject = (JSONObject) jsonObjectLabelInfo.get("label");
                            company = (String) takingLabelJsonObject.get("name");
                        }
                    }
                    if(!albumService.existsByAlbumName(albumName)) {
                        Album album = new Album(albumName, 0, releaseDate, company);
                        albumService.save(album);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            i++;
        }

    }

    private void takeGenres(){

        String[] genreName = loadTextFile(GENRES_FILE);

        int i = 0;
        while(genreName[i] != null) {
            Genre genre = new Genre();
            genre.setGenreName(genreName[i]);
            genreService.save(genre);
            i++;
        }
    }

    private void takeCountries(){
        String[] loadedData = loadTextFile(COUNTRIES_FILE);

        int i = 0;
        while(loadedData[i] != null) {
            String countryData = loadedData[i];
            String[] splitedCountryData = countryData.split(",");
            Country country = new Country(splitedCountryData[3], splitedCountryData[0]);
            countryService.save(country);
            i++;
        }
    }

    private String[] loadTextFile(String fileName) {
        BufferedReader bufferedReader = null;
        int i = 0;
        String[] line = new String[1024];
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((line[i] = bufferedReader.readLine()) != null) {
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return line;
    }

    private void initDataPatterns(){
        knownPatterns.add(new SimpleDateFormat(""));
        knownPatterns.add(new SimpleDateFormat("yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
    }


}
