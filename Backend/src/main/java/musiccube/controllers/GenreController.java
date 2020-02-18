package musiccube.controllers;

import musiccube.entities.Genre;
import musiccube.entities.Song;
import musiccube.services.genre.GenreService;
import musiccube.services.song.SongService;
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

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${serverAddress}")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private SongService songService;

    private String readAboutGenreFromFile(Genre genre){
        try{
            File fileToRead = new File(genre.getAboutGenre());
            FileReader fileReader = new FileReader(fileToRead);
            char[] chars = new char[(int) fileToRead.length()];
            fileReader.read(chars);
            return new String(chars);

        } catch(IOException e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping(
            path = "/genre/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Genre> getById(@PathVariable("id") int id) {
        Optional<Genre> genre = genreService.getById(id);
        if(genre.get().getAboutGenre() != null) {
            String aboutGenre = readAboutGenreFromFile(genre.orElse(null));
            genre.get().setAboutGenre(aboutGenre);
        }

        return genre.isPresent() ?
                ResponseEntity.ok(genre.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/genre",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Genre> getAll() {
        Iterable<Genre> genres = genreService.getAll();
        for (Genre genre: genres) {
            if(genre.getAboutGenre() != null) {
                String aboutGenre = readAboutGenreFromFile(genre);
                genre.setAboutGenre(aboutGenre);
            }
        }
        return genres;
    }

    // --- Get all genres with paging ---
    @GetMapping(
            path = "genre/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Genre> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage
    ) {

        Iterable<Genre> genres;
        if(perPage == null) {
            genres = genreService.getAllPaging(pageNr, Defaults.PAGESIZE);
        } else {
            genres = genreService.getAllPaging(pageNr, perPage);
        }

        for (Genre genre: genres) {
            if(genre.getAboutGenre() != null) {
                genre.setAboutGenre(readAboutGenreFromFile(genre));
            }
        }

        return genres;

    }

    // --- Get by name ---
    @GetMapping(
            path = "/genre/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Genre> getByGenreName(@PathVariable("name") String genreName) {

        Iterable<Genre> genres = genreService.getByGenreName(genreName);
        for (Genre genre: genres) {
            if(genre.getAboutGenre() != null) {
                genre.setAboutGenre(readAboutGenreFromFile(genre));
            }
        }

        return genres;

    }

    @PostMapping("/admin/genre")
    public ResponseEntity<Genre> create(@RequestBody @Valid @NotNull Genre genre) {
        if (genreService.existsByName(genre.getGenreName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        String aboutGenre = genre.getAboutGenre();
        genre.setAboutGenre("");
        Genre genre1 = genreService.save(genre);

        String dir = System.getProperty("user.dir");
        dir = dir.concat("/TxtFiles/AboutGenre/");

        String fileDirWithName = dir.concat(String.valueOf(genre1.getId()).concat(".txt"));

        try{
            File newTextFile = new File(fileDirWithName);
            newTextFile.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(newTextFile);
            fileWriter.write(aboutGenre);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        genre1.setAboutGenre(fileDirWithName);
        genreService.save(genre1);

        return ResponseEntity.ok().body(genre);
    }

    @PutMapping("/admin/genre")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Genre genre) {
        Optional<Genre> genre1 = genreService.getById(genre.getId());
        if (Objects.nonNull(genre1)) {

            String dir = System.getProperty("user.dir");
            dir = dir.concat("/TxtFiles/AboutGenre/");

            String fileDirWithName = genre1.get().getAboutGenre();

            if(fileDirWithName == null){
                fileDirWithName = dir.concat(String.valueOf(genre.getId()).concat(".txt"));
            }


            try{
                File newTextFile = new File(fileDirWithName);
                newTextFile.getParentFile().mkdirs();
                FileWriter fileWriter = new FileWriter(newTextFile);
                fileWriter.write(genre.getAboutGenre());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            genre.setAboutGenre(fileDirWithName);

            genreService.save(genre);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/genre/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Genre genre = genreService.getById(id).orElse(null);
        if(genre != null) {
            Iterable<Song> allSongsWithGenre = songService.getByGenreName(genre.getGenreName());
            for (Song song : allSongsWithGenre){
                song.setGenre(null);
                songService.save(song);
            }

            File file = new File(genre.getAboutGenre());

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

            genreService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
