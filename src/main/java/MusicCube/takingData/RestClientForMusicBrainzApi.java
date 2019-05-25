package MusicCube.takingData;

import MusicCube.entities.Genre;
import MusicCube.entities.Instrument;
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

@RestController
public class RestClientForMusicBrainzApi {

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private GenreService genreService;

    private final String URL = "https://musicbrainz.org/ws/2/";

    private final String INSTRUMENTS = "instrument/?query=type:";
    private final String[] INSTRUMENT_TYPE = {"\"Wind instrument\"", "\"String instrument\"", "\"Percussion instrumen\"", "\"Electronic instrument\"", "\"Other instrument\""};


    private final int LIMIT_INT = 100;
    private final String LIMIT_URL = "&limit=100";
    private final String JSON_TYPE_URL = "&fmt=json";

    private final String GENRES_FILE = ".\\src\\main\\java\\MusicCube\\takingData\\genres.txt";
    
    private RestTemplate restTemplate = new RestTemplate();
    private JSONParser jsonParser = new JSONParser();

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Void> takeData(){


        takeGenres();
        //takeInstruments();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void takeInstruments(){
        String instrumentName;
        String type;

        for(int i = 0; i < INSTRUMENT_TYPE.length; i++) {
            ResponseEntity<String> takeResponseFromApi = restTemplate.getForEntity(URL + INSTRUMENTS + INSTRUMENT_TYPE[i] + LIMIT_URL + JSON_TYPE_URL, String.class);
            try{
                Object responseJsonBody = jsonParser.parse(takeResponseFromApi.getBody());
                JSONObject responseJsonBodyToJsonObj = (JSONObject) responseJsonBody;
                JSONArray takingInstrumentJsonArray = (JSONArray) responseJsonBodyToJsonObj.get("instruments");
                Object[] converteToObjectArray = takingInstrumentJsonArray.toArray();
                JSONObject jsonObject;

                for(int j = 0; j < converteToObjectArray.length; j++){
                    jsonObject = (JSONObject) converteToObjectArray[j];

                    instrumentName = (String) jsonObject.get("name");
                    type = (String) jsonObject.get("type");

                    if(!instrumentService.existsByInstrumentName(instrumentName)) {
                        Instrument instrument = new Instrument(instrumentName, type);
                        instrumentService.save(instrument);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void takeGenres(){

        String genreName;
        String[] textFile = loadTextFile(GENRES_FILE);

        int i = 0;
        while(textFile[i] != null) {
            Genre genre = new Genre();
            genre.setGenreName(textFile[i]);
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


}
