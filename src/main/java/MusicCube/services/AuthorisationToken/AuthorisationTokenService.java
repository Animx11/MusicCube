package MusicCube.services.AuthorisationToken;

import MusicCube.entities.AuthorisationToken;

public interface AuthorisationTokenService {

    AuthorisationToken save(AuthorisationToken authorisationToken);
    void delete(Integer id);

    Iterable<AuthorisationToken> listTokens();

}
