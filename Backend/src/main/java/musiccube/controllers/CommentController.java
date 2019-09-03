package musiccube.controllers;

import musiccube.entities.Comment;
import musiccube.repositories.CommentRepository;
import musiccube.services.comment.CommentService;
import musiccube.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/comment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> getById(@PathVariable("id") int id) {
        Optional<Comment> rate = commentService.getById(id);
        return rate.isPresent() ?
                ResponseEntity.ok(rate.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/comment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Comment> getAll() {
        return commentService.getAll();
    }

    @PostMapping(path = "/comment/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> create(@RequestBody @Valid @NotNull Comment comment, @PathVariable("userName") String userName) {
        if(comment != null){
            comment.setUser(userService.getByUserName(userName).orElse(null));
            if(comment.getUser() != null) {
                commentService.save(comment);
                return ResponseEntity.ok().body(comment);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/comment/{id}")
    public ResponseEntity<Comment> delete(@PathVariable("id") Integer id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/comment")
    public ResponseEntity<Void> editComment(@RequestBody @Valid @NotNull Comment comment){
        Optional<Comment> comment1 = commentService.getById(comment.getId());
        if (Objects.nonNull(comment1)) {
            commentService.save(comment);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Get comments from sections

    @GetMapping(path = "/comment/song", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Comment> getCommentsBySongId(@RequestParam("songId") int id){
        return commentService.getCommentsBySongId(id);
    }

    @GetMapping(path = "/comment/album", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Comment> getCommentsByAlbumId(@RequestParam("albumId") int id){
        return commentService.getCommentsByAlbumId(id);
    }

    @GetMapping(path = "/comment/band", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Comment> getCommentsByBandId(@RequestParam("bandId") int id){
        return commentService.getCommentsByBandId(id);
    }

    @GetMapping(path = "/comment/artist", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Comment> getCommentsByArtistId(@RequestParam("artistId") int id){
        return commentService.getCommentsByArtistId(id);
    }

}
