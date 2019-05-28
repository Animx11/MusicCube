package MusicCube.takingData;


import MusicCube.entities.Band;
import MusicCube.entities.City;
import MusicCube.entities.Country;
import MusicCube.services.band.BandService;
import MusicCube.services.city.CityService;
import MusicCube.services.country.CountryService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;

@RestController
public class MusicbrainzDataLoader {
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private BandService bandService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;

    private final int WAIT_BETWEEN_REQUESTS = 600;
    private final int ITERATIONS = 10;

    @RequestMapping(value = "loadData", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> load() throws InterruptedException {
        String bandName;
        Date bandCreation;
        Band band;
        String mbID;
        String countryCode;
        Country country;
        String countryName;
        City city;
        String cityName;

        for (int iii=0;iii<ITERATIONS;iii++) {
            Thread.sleep(WAIT_BETWEEN_REQUESTS);
            ResponseEntity<String> apiResponse = restTemplate.getForEntity("https://musicbrainz.org/ws/2/artist/?query=type:group&limit=1&offset="+String.valueOf(iii)+"&fmt=json", String.class);
            //System.out.println(apiResponse.getBody());
            JSONObject resJSON = new JSONObject(apiResponse.getBody());
            JSONObject bandJSON = resJSON.getJSONArray("artists").getJSONObject(0);
            System.out.println(bandJSON.get("name"));   // sprawdźmy, kogo dostaliśmy
            mbID = resJSON.getJSONArray("artists").getJSONObject(0).get("id").toString(); // wyciągamy ich id

            bandName = bandJSON.getString("name"); // oraz nazwę zespołu

            String dateString = bandJSON.getJSONObject("life-span").getString("begin");// zabawa z datą
            String[] dateArr = dateString.split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dateArr[0]));// wystarczy sam rok
            bandCreation = calendar.getTime();

            countryCode = bandJSON.getString("country");// kraj
            if (countryService.existsByCode(countryCode)) {// pobrać lub stworzyć, jeśli nie istnieje
                country = countryService.getOneByCode(countryCode);
            } else {
                countryName = bandJSON.getJSONObject("area").getString("name");
                country = new Country(countryName, countryCode);
                countryService.save(country);
            }

            cityName = bandJSON.getJSONObject("begin-area").getString("name");
            if (cityService.exists(cityName)) {
                city = cityService.getByCityName(cityName);
            } else {
                city = new City(cityName, country);
                cityService.save(city);
            }


            band = new Band(bandName, bandCreation, city);
            bandService.save(band);

            Thread.sleep(WAIT_BETWEEN_REQUESTS);
            String newUrl = "http://musicbrainz.org/ws/2/artist/" + mbID + "?inc=artist-rels&fmt=json"; // nowe zapytanie, pobiera członków zespołu
            apiResponse = restTemplate.getForEntity(newUrl, String.class);
            resJSON = new JSONObject(apiResponse.getBody());
            JSONArray relations = resJSON.getJSONArray("relations");

            for (int i = 0; i < relations.length(); i++) {
                JSONObject relation = relations.getJSONObject(i);
                if (relation.getString("type").equals("member of band")) { // trzeba odfiltrować tributy i inne śmieci
                    System.out.println(relation.getJSONObject("artist").getString("name"));
                }
            }

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
