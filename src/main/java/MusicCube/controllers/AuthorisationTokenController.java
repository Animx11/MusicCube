package MusicCube.controllers;


import MusicCube.entities.AuthorisationToken;
import MusicCube.services.AuthorisationToken.AuthorisationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorisationTokenController {



    @Autowired
    private AuthorisationTokenService authorisationTokenService;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<AuthorisationToken> add(@RequestBody @Valid @NotNull AuthorisationToken authorisationToken){
        authorisationTokenService.save(authorisationToken);
        return ResponseEntity.ok().body(authorisationToken);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/token", method = RequestMethod.DELETE)
    public ResponseEntity<AuthorisationToken> delete(@RequestParam("id") Integer id){
        authorisationTokenService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/token_list", method = RequestMethod.GET)
    public Iterable<AuthorisationToken> listTokens(){
        return authorisationTokenService.listTokens();
    }

}
