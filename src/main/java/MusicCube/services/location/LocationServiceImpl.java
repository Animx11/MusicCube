package MusicCube.services.location;

import MusicCube.entities.Location;
import MusicCube.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Optional<Location> getById(int id) {
        return locationRepository.findById(id);
    }
    @Override
    public Iterable<Location> getAll() {
        return locationRepository.findAll();
    }
    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }
    @Override
    public void delete(int id) {
        locationRepository.deleteById(id);
    }
    @Override
    public Iterable<Location> getByCity(String city){
        return locationRepository.findByCity(city);
    }
}
