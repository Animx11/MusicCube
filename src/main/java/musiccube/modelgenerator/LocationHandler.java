package musiccube.modelgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import musiccube.deserializers.CountryDeserializer;
import musiccube.entities.City;
import musiccube.entities.Country;
import musiccube.services.city.CityService;
import musiccube.services.country.CountryService;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class LocationHandler {
    private static LocationHandler instance = null;

    private Logger logger = Logger.getLogger(LocationHandler.class);
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;

    private LocationHandler() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Country.class, new CountryDeserializer());
        mapper.registerModule(module);
    }
    public static LocationHandler getInstance() {
        if (instance == null)
            instance = new LocationHandler();
        return instance;
    }

    /*
    Returns existing localization, or creates new one
     */
    public City getCity(String countryId, String areaId) throws IOException, InterruptedException {
        Thread.sleep(600);
        ResponseEntity<String> response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/area/"+countryId+Constants.FMT,String.class);
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
            logger.info("Country "+country.getCountryName()+Constants.SAVED);
        }

        Thread.sleep(600);
        response = restTemplate.getForEntity("https://musicbrainz.org/ws/2/area/"+areaId+Constants.FMT,String.class);
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
            logger.info("City/area "+city.getCityName()+Constants.SAVED);
        }
        return city;

    }
}
