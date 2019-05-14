package MusicCube.services.user;


import MusicCube.entities.User;
import MusicCube.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    @Override
    public User getById(int id){
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    @Override
    public Iterable<User> listUsers(){
        return userRepository.findAll();
    }

    @Override
    public String getPasswordByUserName(String userName){
        return userRepository.findPasswordByUserName(userName);
    }

    @Override
    public String getUserPermissionByUserName(String userName){
        return userRepository.findUSerPermissionByUserName(userName);
    }

}
