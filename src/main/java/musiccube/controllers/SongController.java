package musiccube.controllers;

import musiccube.entities.*;
import musiccube.services.comment.CommentService;
import musiccube.services.rate.RateService;
import musiccube.services.song.SongService;
import musiccube.services.songauthorship.SongAuthorshipService;
import musiccube.services.songinstrument.SongInstrumentService;
import musiccube.services.userFavorites.UserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class  SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RateService rateService;

    @Autowired
    private SongAuthorshipService songAuthorshipService;

    @Autowired
    private SongInstrumentService songInstrumentService;

    @Autowired
    private UserFavoritesService userFavoritesService;

    private String readLyricFromFile(Song song){
        try{
            File fileToRead = new File(song.getSongLyrics());
            FileReader fileReader = new FileReader(fileToRead);
            char[] chars = new char[(int) fileToRead.length()];
            fileReader.read(chars);
            return new String(chars);

        } catch(IOException e) {
            System.out.println(e);
            return null;
        }
    }


    // --- Get by id ---
    @GetMapping(
            path = "/song/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Song> getById(@PathVariable("id") int id) {
        Optional<Song> song = songService.getById(id);
        if(song.get().getSongLyrics() != null) {
            String lyrics = readLyricFromFile(song.orElse(null));
            song.get().setSongLyrics(lyrics);
        }

        return song.isPresent() ?
                ResponseEntity.ok(song.get()) :
                ResponseEntity.notFound().build();
    }

    // --- Get all songs ---
    @GetMapping(
            path = "/song",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Song> getAll() {

        Iterable<Song> songs = songService.getAll();
        for (Song song: songs) {
            if(song.getSongLyrics() != null) {
                String lyrics = readLyricFromFile(song);
                song.setSongLyrics(lyrics);
            }
        }

        return songs;
    }

    // --- Get all songs with paging ---
    @GetMapping(
            path = "song/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Song> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage) {
        Iterable<Song> songs;
        if(perPage == null){
            songs = songService.getAllPaging(pageNr, Defaults.PAGESIZE);
        } else {
            songs = songService.getAllPaging(pageNr, perPage);
        }

        for (Song song: songs) {
            if(song.getSongLyrics() != null) {
                String lyrics = readLyricFromFile(song);
                song.setSongLyrics(lyrics);
            }
        }

        return songs;

    }

    // --- Get by song name ---
    @GetMapping(
            path = "/song/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Song> getBySongName(@PathVariable("name") String songName) {

        Iterable<Song> songs = songService.getBySongName(songName);
        for (Song song: songs) {
            if(song.getSongLyrics() != null) {
                String lyrics = readLyricFromFile(song);
                song.setSongLyrics(lyrics);
            }
        }

        return songs;
    }

/**********************************************************************************/

    @PostMapping("/admin/song")
    public ResponseEntity<Song> create(@RequestBody @Valid @NotNull Song song) {

        String lyricToSave = song.getSongLyrics();

        song.setSongLyrics("");

        Song song1 = songService.save(song);

        String dir = System.getProperty("user.dir");
        dir = dir.concat("\\Lyrics\\");



        String fileDirWithName = dir.concat(String.valueOf(song1.getId()).concat(".txt"));

        try{
            File newTextFile = new File(fileDirWithName);
            newTextFile.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(newTextFile);
            fileWriter.write(lyricToSave);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        song1.setSongLyrics(fileDirWithName);

        songService.save(song1);
        return ResponseEntity.ok().body(song1);
    }

    @PutMapping("/admin/song")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Song song) {
        Optional<Song> song1 = songService.getById(song.getId());
        if (Objects.nonNull(song1)) {

            String dir = System.getProperty("user.dir");
            dir = dir.concat("\\Lyrics\\");
            String fileDirWithName = song1.get().getSongLyrics();

            try{
                File newTextFile = new File(fileDirWithName);
                FileWriter fileWriter = new FileWriter(newTextFile);
                fileWriter.write(song.getSongLyrics());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }

            song.setSongLyrics(fileDirWithName);

            songService.save(song);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/song/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        Song song = songService.getById(id).orElse(null);
        if(song != null) {
            Iterable<Comment> allSongComments = commentService.getCommentsBySongId(song.getId());
            for (Comment comment : allSongComments) {
                commentService.delete(comment.getId());
            }
            Iterable<Rate> allSongRates = rateService.getRatesBySongId(song.getId());
            for (Rate rate : allSongRates) {
                rateService.delete(rate.getId());
            }
            Iterable<SongAuthorship> allSongAutorships = songAuthorshipService.getBySongId(song.getId());
            for (SongAuthorship songAuthorship : allSongAutorships) {
                songAuthorshipService.delete(songAuthorship.getId());
            }
            Iterable<SongInstrument> allSongInstruments = songInstrumentService.getBySongId(song.getId());
            for (SongInstrument songInstrument : allSongInstruments) {
                songInstrumentService.delete(songInstrument.getId());
            }
            Iterable<UserFavorites> allUserFavoritesLists = userFavoritesService.getAll();
            for (UserFavorites userFavorites : allUserFavoritesLists) {
                Set<Song> userFavoritesSongs = userFavorites.getFavoriteSongs();
                if(userFavoritesSongs.contains(song)) {
                    userFavorites.deleteSongFromFavorites(song);
                }
            }

            File file = new File(song.getSongLyrics());

            System.gc();
            if(new File("./__tmp.txt").delete()){
                System.out.println("OK");
            }

            if(file.delete())
            {
                System.out.println("File deleted successfully");
            }
            else
            {
                System.out.println("Failed to delete the file");
            }

            songService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
