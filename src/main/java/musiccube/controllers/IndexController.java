package musiccube.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin("origins=${serverAddress}")
public class IndexController {
  @GetMapping(
    path="",
    produces=MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<String> index() {
    return ResponseEntity.ok("index");
  }
}
