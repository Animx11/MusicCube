package MusicCube.Services.Users;

import MusicCube.Entities.Users;

public interface UsersService {

    Users save(Users user);
    void delete(Integer id);

    Users getById(int id);
    Iterable<Users> getByUserName(String userName);
    String getPasswordByUserName(String userName);
    String getUserPermissionByUserName(String userName);
    Iterable<Users> listUsers();

}
