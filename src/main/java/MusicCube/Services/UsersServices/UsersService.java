package MusicCube.Services.UsersServices;

import MusicCube.Entities.Users;

import java.util.Optional;

public interface UsersService {

    Users save(Users user);
    void delete(Integer id);

    Users getById(int id);
    Iterable<Users> getByUserName(String userName);
    Iterable<Users> listUsers();

}
