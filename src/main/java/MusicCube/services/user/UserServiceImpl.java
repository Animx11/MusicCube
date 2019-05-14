package MusicCube.services.user;


import MusicCube.entities.User;
import MusicCube.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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
    public Optional<User> getByUserName(String userName){
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
        return null;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("User not found with: " + userName));
        return UserPrinciple.build(user);

    }

}
