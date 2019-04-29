package MusicCube.services.AuthorisationToken;

import MusicCube.entities.AuthorisationToken;
import MusicCube.repositories.AuthorisationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorisationTokenServiceImplementation implements AuthorisationTokenService {

    @Autowired
    private AuthorisationTokenRepository authorisationTokenRepository;

    @Override
    public AuthorisationToken save(AuthorisationToken authorisationToken){
       return authorisationTokenRepository.save(authorisationToken);
    }

    @Override
    public void delete(Integer id){
        authorisationTokenRepository.deleteById(id);
    }

    @Override
    public Iterable<AuthorisationToken> listTokens(){
        return authorisationTokenRepository.findAll();
    }


}
