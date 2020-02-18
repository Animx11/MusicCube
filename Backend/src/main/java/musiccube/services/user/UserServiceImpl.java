package musiccube.services.user;


import musiccube.entities.User;
import musiccube.repositories.RoleRepository;
import musiccube.repositories.UserRepository;
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
    public Optional<User> getOneByUserName(String userName){
        return userRepository.findOneByUserName(userName);
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
    public boolean existsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findOneByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("User not found with: " + userName));
        return UserPrinciple.build(user);

    }

}
