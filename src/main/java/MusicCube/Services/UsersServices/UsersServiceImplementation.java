package MusicCube.Services.UsersServices;


import MusicCube.Entities.Users;
import MusicCube.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
