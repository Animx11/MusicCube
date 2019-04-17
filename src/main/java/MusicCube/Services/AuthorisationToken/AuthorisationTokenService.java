package MusicCube.Services.AuthorisationToken;

import MusicCube.Entities.AuthorisationToken;

public interface AuthorisationTokenService {

    AuthorisationToken save(AuthorisationToken authorisationToken);
    void delete(Integer id);

    Iterable<AuthorisationToken> listTokens();

}
