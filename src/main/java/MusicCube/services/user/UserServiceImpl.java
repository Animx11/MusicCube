package MusicCube.services.user;


import MusicCube.entities.Role;
import MusicCube.entities.RoleName;
import MusicCube.entities.User;
import MusicCube.repositories.RoleRepository;
import MusicCube.repositories.UserRepository;
import MusicCube.user.UserProfile;
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

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getById(int id){
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
    public boolean existsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("User not found with: " + userName));
        return UserPrinciple.build(user);

    }

}
