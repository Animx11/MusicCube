package MusicCube.takingData;

import MusicCube.entities.Album;
import MusicCube.entities.Country;
import MusicCube.entities.Genre;
import MusicCube.entities.Instrument;
import MusicCube.services.album.AlbumService;
import MusicCube.services.country.CountryService;
import MusicCube.services.genre.GenreService;
import MusicCube.services.instrument.InstrumentService;
import org.json.JSONArray;
import org.json.JSONObject;
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
    private final String ALBUMS = "release/?query=*";
    private final String COUNTRY = "area/?query=type:Country";
    private final String CITIES = "area/?query=type:City";
    //private final String[] INSTRUMENT_TYPE = {"\"Wind instrument\"", "\"String instrument\"", "\"Percussion instrumen\"", "\"Electronic instrument\"", "\"Other instrument\""};


    private final int OFFSET_JUMP = 80;
    private final int WAIT_BETWEEN_REQUESTS = 650;

    private final String LIMIT_URL = "&limit=100";
    private final String JSON_TYPE_URL = "&fmt=json";
    private final String OFFSET = "&offset=";

    private final String GENRES_FILE = ".\\src\\main\\java\\MusicCube\\takingData\\genres.txt";
    private final String COUNTRIES_FILE = ".\\src\\main\\java\\MusicCube\\takingData\\countries.txt";
    
    private RestTemplate restTemplate = new RestTemplate();
    private List<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Void> takeData(){

        initDataPatterns();

        //takeGenres();

        try {
            takeCountries();
            takeInstruments();
            //takeAlbums();
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
            Thread.sleep(WAIT_BETWEEN_REQUESTS);
            String offsetNum = Integer.toString(i);
            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + INSTRUMENTS + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);
            try{

                JSONObject responseJson = new JSONObject(takeResponseFromApi.getBody());
                JSONArray takingInstrumentsJsonArray = responseJson.getJSONArray("instruments");

                if(takingInstrumentsJsonArray.length() == 0){
                    notEmpty = false;
                }

                for(int j = 0; j < takingInstrumentsJsonArray.length(); j++) {
                    instrumentName = takingInstrumentsJsonArray.getJSONObject(j)
                            .getString("name");
                    try {
                        type = takingInstrumentsJsonArray.getJSONObject(j)
                                .getString("type");
                    } catch (Exception e){
                        type = null;
                    }
                    if (!instrumentService.existsByInstrumentName(instrumentName)) {
                        Instrument instrument = new Instrument(instrumentName, type);
                        instrumentService.save(instrument);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            i = i + OFFSET_JUMP;
        }
    }
    /*
        private void takeAlbums() throws InterruptedException{
            String albumName = "";
            Date releaseDate = new Date();
            String company = "";
            int i = 0;

            //DateFormat dateFormat = new SimpleDateFormat("yyyy");

            boolean notEmpty = true;

            while (notEmpty) {
                Thread.sleep(WAIT_BETWEEN_REQUESTS);
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
                            }catch (ParseException e){
                            }
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
                            Album album;
                            if(releaseDate != null) {
                                album = new Album(albumName, 0, releaseDate, company);
                            }
                            else {
                                album = new Album(albumName, 0, company);
                            }
                            albumService.save(album);
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                i = i + OFFSET_JUMP;
            }

        }
    */
    private void takeCountries() throws InterruptedException{
        boolean notEmpty = true;
        String countryName;
        String code;
        int i = 0;

        while(notEmpty){
            Thread.sleep(WAIT_BETWEEN_REQUESTS);
            String offsetNum = Integer.toString(i);
            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + COUNTRY + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);

            try{

                JSONObject responseJson = new JSONObject(takeResponseFromApi.getBody());
                JSONArray takingAreasJsonArray = responseJson.getJSONArray("areas");

                if(takingAreasJsonArray.length() == 0){
                    notEmpty = false;
                }

                for(int j = 0; j < takingAreasJsonArray.length(); j++) {
                    countryName = takingAreasJsonArray.getJSONObject(j)
                            .getString("name");
                    try {
                        code = takingAreasJsonArray.getJSONObject(j)
                                .getJSONArray("iso-3166-1-codes").getString(0);
                    } catch (Exception e) {
                        code = null;
                    }
                    if (!countryService.existsByCountryName(countryName)) {
                        Country country = new Country(countryName, code);
                        countryService.save(country);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            i = i + OFFSET_JUMP;
        }
    }

    //TODO Ze względu na ich bazę danych muszę wziąść z innego miejsca bazę danych, która ma połączone państwa z miastami lub zrobić nowe entitie z miastami
/*
    private void takeCities() throws InterruptedException{
        boolean notEmpty = true;
        String countryName;
        String cityName;
        int i = 0;

        while(notEmpty){
            Thread.sleep(WAIT_BETWEEN_REQUESTS);
            String offsetNum = Integer.toString(i);
            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + CITIES + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);

            try{
                org.json.JSONObject responseJson = new org.json.JSONObject(takeResponseFromApi.getBody());
                org.json.JSONArray takingAreasJsonArray = responseJson.getJSONArray("areas");

                if(takingAreasJsonArray.length() == 0){
                    notEmpty = false;
                }

                for(int j = 0; j < takingAreasJsonArray.length(); j++){
                    cityName = takingAreasJsonArray.getJSONObject(j).getString("name");
                    countryName = takingAreasJsonArray.getJSONObject(j)
                            .getJSONArray("relation-list").getJSONObject(0)
                            .getJSONArray("relations").getJSONObject(0)
                            .getJSONObject("area").getString("name");
                    System.out.println(countryName);

                }

            } catch (Exception e){
                e.printStackTrace();
            }

            i = i + OFFSET_JUMP;
        }

    }
*/

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
