//package musiccube.takingData;
//
//import musiccube.entities.*;
//import musiccube.services.album.AlbumService;
//import musiccube.services.artist.ArtistService;
//import musiccube.services.band.BandService;
//import musiccube.services.country.CountryService;
//import musiccube.services.genre.GenreService;
//import musiccube.services.instrument.InstrumentService;
//import musiccube.services.song.SongService;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//@RestController
//public class RestClientForMusicBrainzApi {
//
//    @Autowired
//    private InstrumentService instrumentService;
//
//    @Autowired
//    private GenreService genreService;
//
//    @Autowired
//    private CountryService countryService;
//
//    @Autowired
//    private AlbumService albumService;
//
//    @Autowired
//    private ArtistService artistService;
//
//    @Autowired
//    private BandService bandService;
//
//    @Autowired
//    private SongService songService;
//
//    private final String URL = "https://musicbrainz.org/ws/2/";
//
//    private final String INSTRUMENTS = "instrument/?query=*";
//    private final String ALBUMS = "release/?query=*";
//    private final String COUNTRY = "area/?query=type:Country";
//    private final String CITIES = "area/?query=type:City";
//    private final String ARTISTS = "artist/?query=type:Person";
//    private final String BANDS = "artist/?query=type:Group";
//    private final String SONGS = "recording/?query=*";
//    //private final String[] INSTRUMENT_TYPE = {"\"Wind instrument\"", "\"String instrument\"", "\"Percussion instrumen\"", "\"Electronic instrument\"", "\"Other instrument\""};
//
//
//    private final int OFFSET_JUMP = 80;
//    private final int WAIT_BETWEEN_REQUESTS = 650;
//    private final int COUNT_OF_TEASTED_OBJECTS = 1000;
//
//    private final String LIMIT_URL = "&limit=100";
//    private final String JSON_TYPE_URL = "&fmt=json";
//    private final String OFFSET = "&offset=";
//
//    private final String GENRES_FILE = ".\\src\\main\\java\\musiccube\\takingData\\genres.txt";
//    private final String COUNTRIES_FILE = ".\\src\\main\\java\\musiccube\\takingData\\countries.txt";
//
//    private RestTemplate restTemplate = new RestTemplate();
//    private List<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public ResponseEntity<Void> takeData() throws InterruptedException{
//
//        initDataPatterns();
//
//        takeGenres();
//
//        try {
//            takeArtists();
//            takeCountries();
//            takeInstruments();
//            takeAlbums();
//            takeBands();
//            takeSongs();
//        }
//        catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    // Must refactor
//    private void takeArtists() throws InterruptedException{
//        boolean notEmpty = true;
//        String stageName = "";
//        String firstNames = "";
//        String lastName = "";
//        Date birthDate = null;
//        int i = 0;
//
//        while(notEmpty && i < COUNT_OF_TEASTED_OBJECTS){
//            Thread.sleep(WAIT_BETWEEN_REQUESTS);
//            String offsetNum = Integer.toString(i);
//            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + ARTISTS + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);
//
//            try{
//
//                JSONObject responseJson = new JSONObject(takeResponseFromApi.getBody());
//                JSONArray takingArtistsJsonArray = responseJson.getJSONArray("artists");
//                if(takingArtistsJsonArray.length() == 0){
//                    notEmpty = false;
//                }
//
//                for(int j = 0; j < takingArtistsJsonArray.length(); j++) {
//                    String fullName = takingArtistsJsonArray.getJSONObject(j)
//                            .getString("sort-name");
//                    String[] splitedFullName = fullName.split(", ");
//                    if(splitedFullName.length > 1) {
//                        firstNames = splitedFullName[1];
//                        lastName = splitedFullName[0];
//                    }
//                    else{
//                        stageName = fullName;
//                        try {
//                            for (int k = 0; k < takingArtistsJsonArray.getJSONObject(j).getJSONArray("aliases").length(); k++) {
//
//                                try {
//                                    if (takingArtistsJsonArray.getJSONObject(j).getJSONArray("aliases").getJSONObject(k).get("type").toString().equals("Legal name") ||
//                                            (takingArtistsJsonArray.getJSONObject(j).getJSONArray("aliases").getJSONObject(k).get("type").toString().equals("Artist name") &&
//                                                    takingArtistsJsonArray.getJSONObject(j).getJSONArray("aliases").getJSONObject(k).get("locale").toString().equals("en"))) {
//                                        fullName = takingArtistsJsonArray.getJSONObject(j)
//                                                .getJSONArray("aliases").getJSONObject(k)
//                                                .getString("name");
//                                    }
//                                } catch (Exception e){
//                                    e.printStackTrace();
//                                }
//
//                            }
//
//                            splitedFullName = fullName.split(" ");
//
//                            for (int k = 0; k < splitedFullName.length - 1; k++) {
//                                if (k != splitedFullName.length - 2) {
//                                    firstNames = firstNames + splitedFullName[k] + " ";
//                                } else {
//                                    firstNames = firstNames + splitedFullName[k];
//                                }
//                            }
//                        lastName = splitedFullName[splitedFullName.length-1];
//
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//
//                    for (SimpleDateFormat pattern : knownPatterns) {
//                        try {
//                            try {
//                                birthDate = pattern.parse(takingArtistsJsonArray.getJSONObject(j)
//                                        .getJSONObject("life-span").getString("begin"));
//                            } catch (ParseException e) {
//                            }
//                        } catch (Exception e){ }
//                    }
//
//                    if(!artistService.existsArtistByArtistFirstNamesAndLastName(firstNames, lastName) || !artistService.existsArtistByStageName(stageName)) {
//                        Artist artist = new Artist(null, firstNames, lastName, birthDate, stageName);
//                        artistService.save(artist);
//                    }
//
//                    firstNames = "";
//                    lastName = "";
//                    stageName = "";
//                    birthDate = null;
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//            i = i + OFFSET_JUMP;
//        }
//    }
//
//    private void takeBands() throws InterruptedException{
//        boolean notEmpty = true;
//        String bandName;
//        Date creationDate = null;
//        int i = 0;
//
//        while(notEmpty && i < COUNT_OF_TEASTED_OBJECTS){
//            Thread.sleep(WAIT_BETWEEN_REQUESTS);
//            String offsetNum = Integer.toString(i);
//            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + BANDS + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);
//
//            try{
//
//                JSONObject responseJson = new JSONObject(takeResponseFromApi.getBody());
//                JSONArray takingBandsJsonArray = responseJson.getJSONArray("artists");
//
//                if(takingBandsJsonArray.length() == 0){
//                    notEmpty = false;
//                }
//
//                for(int j = 0; j < takingBandsJsonArray.length(); j++) {
//                    bandName = takingBandsJsonArray.getJSONObject(j)
//                            .getString("name");
//
//                    for (SimpleDateFormat pattern : knownPatterns) {
//                        try {
//                            try {
//                                creationDate = pattern.parse(takingBandsJsonArray.getJSONObject(j).getJSONObject("life-span").getString("begin"));
//                            } catch (ParseException e) {
//                            }
//                        } catch (Exception e){
//                            creationDate = null;
//                        }
//                    }
//
//
//                    if (!bandService.existsByBandName(bandName)) {
//                        Band band = new Band(bandName, creationDate);
//                        bandService.save(band);
//                    }
//                }
//
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
//
//            i = i + OFFSET_JUMP;
//        }
//
//    }
//
//    private void takeSongs() throws InterruptedException{
//        int songLengthSeconds;
//        String songName;
//        Album album = null;
//        Band band = null;
//        String bandToFind;
//        String albumToFind;
//
//        boolean notEmpty = true;
//        int i = 0;
//
//        while (notEmpty && i < COUNT_OF_TEASTED_OBJECTS){
//            Thread.sleep(WAIT_BETWEEN_REQUESTS);
//            String offsetNum = Integer.toString(i);
//            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + SONGS + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);
//
//            try{
//                JSONObject responseJson = new JSONObject(takeResponseFromApi.getBody());
//                JSONArray takingSongsJsonArray = responseJson.getJSONArray("recordings");
//
//                if(takingSongsJsonArray.length() == 0){
//                    notEmpty = false;
//                }
//                for(int j = 0; j < takingSongsJsonArray.length(); j++) {
//                    songName = takingSongsJsonArray.getJSONObject(j)
//                            .getString("title");
//                    try {
//                        songLengthSeconds = takingSongsJsonArray.getJSONObject(j)
//                                .getInt("length");
//                    }catch (Exception e){
//                        songLengthSeconds = 0;
//                    }
//                    try {
//                        bandToFind = takingSongsJsonArray.getJSONObject(j)
//                                .getJSONArray("artist-credit").getJSONObject(0)
//                                .getJSONObject("artist").getString("name");
//                        Iterable<Band> bands = bandService.getByBandName(bandToFind);
//                        if(bands.iterator().hasNext()){
//                            band = bands.iterator().next();
//                        }
//                    }catch (Exception e){
//                        band = null;
//                    }
//
//                    try{
//                        albumToFind = takingSongsJsonArray.getJSONObject(j)
//                                .getJSONArray("releases").getJSONObject(0)
//                                .getString("title");
//                        Iterable<Album> albums = albumService.getByAlbumName(albumToFind);
//                        if(albums.iterator().hasNext()){
//                            album = albums.iterator().next();
//                        }
//                    }catch (Exception e){
//                        album = null;
//                    }
//
//                    if (!songService.existsBySongName(songName)) {
//                        Song song = new Song(songName, songLengthSeconds, album, band);
//                        songService.save(song);
//                    }
//
//                }
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//
//            i = i + OFFSET_JUMP;
//        }
//
//    }
//
//    private void takeInstruments() throws InterruptedException{
//        String instrumentName;
//        String type;
//        int i = 0;
//
//        boolean notEmpty = true;
//
//        while (notEmpty && i < COUNT_OF_TEASTED_OBJECTS) {
//            Thread.sleep(WAIT_BETWEEN_REQUESTS);
//            String offsetNum = Integer.toString(i);
//            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + INSTRUMENTS + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);
//            try{
//
//                JSONObject responseJson = new JSONObject(takeResponseFromApi.getBody());
//                JSONArray takingInstrumentsJsonArray = responseJson.getJSONArray("instruments");
//
//                if(takingInstrumentsJsonArray.length() == 0){
//                    notEmpty = false;
//                }
//
//                for(int j = 0; j < takingInstrumentsJsonArray.length(); j++) {
//                    instrumentName = takingInstrumentsJsonArray.getJSONObject(j)
//                            .getString("name");
//                    try {
//                        type = takingInstrumentsJsonArray.getJSONObject(j)
//                                .getString("type");
//                    } catch (Exception e){
//                        type = null;
//                    }
//                    if (!instrumentService.existsByInstrumentName(instrumentName)) {
//                        Instrument instrument = new Instrument(instrumentName, type);
//                        instrumentService.save(instrument);
//                    }
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            i = i + OFFSET_JUMP;
//        }
//    }
//
//    private void takeAlbums() throws InterruptedException{
//        String albumName;
//        Date releaseDate = new Date();
//        String company;
//        int i = 0;
//
//        boolean notEmpty = true;
//
//        while (notEmpty && i < COUNT_OF_TEASTED_OBJECTS) {
//            Thread.sleep(WAIT_BETWEEN_REQUESTS);
//            String offsetNum = Integer.toString(i);
//            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + ALBUMS + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);
//
//            try{
//                JSONObject responseJson = new JSONObject(takeResponseFromApi.getBody());
//                JSONArray takingReleasesJsonArray = responseJson.getJSONArray("releases");
//
//                if(takingReleasesJsonArray.length() == 0){
//                    notEmpty = false;
//                }
//
//                for(int j = 0; j < takingReleasesJsonArray.length(); j++) {
//                    albumName = takingReleasesJsonArray.getJSONObject(j)
//                            .getString("title");
//                    try {
//                        company = takingReleasesJsonArray.getJSONObject(j)
//                                .getJSONArray("label-info").getJSONObject(0)
//                                .getJSONObject("label").getString("name");
//                    }
//                    catch (Exception e){
//                        company = null;
//                    }
//                    for (SimpleDateFormat pattern : knownPatterns) {
//                        try {
//                            try {
//                                releaseDate = pattern.parse(takingReleasesJsonArray.getJSONObject(j).getString("date"));
//                            } catch (ParseException e) {
//                            }
//                        } catch (Exception e){
//                            releaseDate = null;
//                        }
//                    }
//                    if(!albumService.existsByAlbumName(albumName)) {
//                        Album album = new Album(albumName, 0, releaseDate, company);
//                        albumService.save(album);
//                    }
//
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//            i = i + OFFSET_JUMP;
//
//        }
//
//        }
//
//    private void takeCountries() throws InterruptedException{
//        boolean notEmpty = true;
//        String countryName;
//        String code;
//        int i = 0;
//
//        while(notEmpty && i < COUNT_OF_TEASTED_OBJECTS){
//            Thread.sleep(WAIT_BETWEEN_REQUESTS );
//            String offsetNum = Integer.toString(i);
//            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + COUNTRY + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);
//
//            try{
//
//                JSONObject responseJson = new JSONObject(takeResponseFromApi.getBody());
//                JSONArray takingAreasJsonArray = responseJson.getJSONArray("areas");
//
//                if(takingAreasJsonArray.length() == 0){
//                    notEmpty = false;
//                }
//
//                for(int j = 0; j < takingAreasJsonArray.length(); j++) {
//                    countryName = takingAreasJsonArray.getJSONObject(j)
//                            .getString("name");
//                    try {
//                        code = takingAreasJsonArray.getJSONObject(j)
//                                .getJSONArray("iso-3166-1-codes").getString(0);
//                    } catch (Exception e) {
//                        code = null;
//                    }
//                    if (!countryService.existsByCountryName(countryName)) {
//                        Country country = new Country(countryName, code);
//                        countryService.save(country);
//                    }
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//            i = i + OFFSET_JUMP;
//        }
//    }
//
//    //TODO Ze względu na ich bazę danych muszę wziąść z innego miejsca bazę danych, która ma połączone państwa z miastami lub zrobić nowe entitie z miastami
//    /*
//    private void takeCities() throws InterruptedException{
//        boolean notEmpty = true;
//        String countryName;
//        String cityName;
//        int i = 0;
//
//        while(notEmpty){
//            Thread.sleep(WAIT_BETWEEN_REQUESTS);
//            String offsetNum = Integer.toString(i);
//            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + CITIES + LIMIT_URL + OFFSET + offsetNum + JSON_TYPE_URL, String.class);
//
//            try{
//                org.json.JSONObject responseJson = new org.json.JSONObject(takeResponseFromApi.getBody());
//                org.json.JSONArray takingAreasJsonArray = responseJson.getJSONArray("areas");
//
//                if(takingAreasJsonArray.length() == 0){
//                    notEmpty = false;
//                }
//
//                for(int j = 0; j < takingAreasJsonArray.length(); j++){
//                    cityName = takingAreasJsonArray.getJSONObject(j).getString("name");
//                    countryName = takingAreasJsonArray.getJSONObject(j)
//                            .getJSONArray("relation-list").getJSONObject(0)
//                            .getJSONArray("relations").getJSONObject(0)
//                            .getJSONObject("area").getString("name");
//                    System.out.println(countryName);
//
//                }
//
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//
//            i = i + OFFSET_JUMP;
//        }
//
//    }
//*/
//
//    private void takeGenres(){
//
//        String[] genreName = loadTextFile(GENRES_FILE);
//
//        int i = 0;
//        while(genreName[i] != null) {
//            Genre genre = new Genre();
//            genre.setGenreName(genreName[i]);
//            genreService.save(genre);
//            i++;
//        }
//    }
//
//    private String[] loadTextFile(String fileName) {
//        BufferedReader bufferedReader = null;
//        int i = 0;
//        String[] line = new String[1024];
//        try {
//            bufferedReader = new BufferedReader(new FileReader(fileName));
//            while ((line[i] = bufferedReader.readLine()) != null) {
//                i++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (bufferedReader != null) {
//                    bufferedReader.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return line;
//    }
//
//    private void initDataPatterns(){
//        knownPatterns.add(new SimpleDateFormat(""));
//        knownPatterns.add(new SimpleDateFormat("yyyy"));
//        knownPatterns.add(new SimpleDateFormat("yyyy-MM"));
//        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
//    }
//
//
//}
