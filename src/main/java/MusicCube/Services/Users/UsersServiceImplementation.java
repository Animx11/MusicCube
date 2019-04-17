package MusicCube.Services.Users;


import MusicCube.Entities.Users;
import MusicCube.Repositories.UsersRepository;
import MusicCube.Services.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImplementation implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users save(Users user){
        return usersRepository.save(user);
    }

    @Override
    public void delete(Integer id){
        usersRepository.deleteById(id);
    }

    @Override
    public Users getById(int id){
        return usersRepository.findById(id);
    }

    @Override
    public Iterable<Users> getByUserName(String userName){
        return usersRepository.findByUserName(userName);
    }

    @Override
    public Iterable<Users> listUsers(){
        return usersRepository.findAll();
    }

    @Override
    public String getPasswordByUserName(String userName){
        return usersRepository.findPasswordByUserName(userName);
    }

    @Override
    public String getUserPermissionByUserName(String userName){
        return usersRepository.findUSerPermissionByUserName(userName);
    }

}
