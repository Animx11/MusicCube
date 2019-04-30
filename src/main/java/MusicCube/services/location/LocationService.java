package MusicCube.services.location;

import MusicCube.entities.Location;

import java.util.Optional;

public interface LocationService {

    Optional<Location> getById(int id);
    Iterable<Location> getAll();
    Location save(Location location);
    void delete(int id);

    Iterable<Location> getByCity(String city);

}
