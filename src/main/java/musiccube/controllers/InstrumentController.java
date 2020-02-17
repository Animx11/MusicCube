package musiccube.controllers;

import musiccube.entities.ArtistInstrument;
import musiccube.entities.Instrument;
import musiccube.entities.SongInstrument;
import musiccube.services.artistinstrument.ArtistInstrumentService;
import musiccube.services.instrument.InstrumentService;
import musiccube.services.songinstrument.SongInstrumentService;
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
public class InstrumentController {
    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private SongInstrumentService songInstrumentService;

    @Autowired
    private ArtistInstrumentService artistInstrumentService;

    private String readAboutInstrumentFromFile(Instrument instrument){
        try{
            File fileToRead = new File(instrument.getAboutInstrument());
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
            path = "/instrument/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Instrument> getById(@PathVariable("id") int id) {
        Optional<Instrument> instrument = instrumentService.getById(id);
        if(instrument.get().getAboutInstrument() != null) {
            instrument.get().setAboutInstrument(readAboutInstrumentFromFile(instrument.orElse(null)));
        }

        return instrument.isPresent() ?
                ResponseEntity.ok(instrument.get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(
            path = "/instrument",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Instrument> getAll() {

        Iterable<Instrument> instruments = instrumentService.getAll();
        for (Instrument instrument: instruments) {
            if(instrument.getAboutInstrument() != null) {
                instrument.setAboutInstrument(readAboutInstrumentFromFile(instrument));
            }
        }

        return instruments;

    }
    
    // --- Get all instruments with paging ---
    @GetMapping(
            path = "instrument/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Instrument> getAllPaging(
            @PathVariable("page") Integer pageNr,
            @RequestParam(name = "size", required = false) Integer perPage
    ) {

        Iterable<Instrument> instruments;
        if(perPage == null) {
            instruments = instrumentService.getAllPaging(pageNr, Defaults.PAGESIZE);
        } else {
            instruments = instrumentService.getAllPaging(pageNr, perPage);
        }

        for (Instrument instrument: instruments) {
            if(instrument.getAboutInstrument() != null) {
                instrument.setAboutInstrument(readAboutInstrumentFromFile(instrument));
            }
        }

        return instruments;

    }

    // --- Get by instrument name ---
    @GetMapping(
            path = "/instrument/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Instrument> getByInstrumentName(@PathVariable("name") String instrumentName) {

        Iterable<Instrument> instruments = instrumentService.getByInstrumentName(instrumentName);
        for (Instrument instrument: instruments) {
            if(instrument.getAboutInstrument() != null){
                instrument.setAboutInstrument(readAboutInstrumentFromFile(instrument));
            }
        }

        return instruments;

    }

    @PostMapping("/admin/instrument")
    public ResponseEntity<Instrument> create(@RequestBody @Valid @NotNull Instrument instrument) {

        String aboutInstrumentToSave = instrument.getAboutInstrument();
        instrument.setAboutInstrument("");

        Instrument instrument1 = instrumentService.save(instrument);

        String dir = System.getProperty("user.dir");
        dir = dir.concat("\\AboutInstrument\\");

        String fileDirWithName = dir.concat(String.valueOf(instrument1.getId()).concat(".txt"));

        try{
            File newTextFile = new File(fileDirWithName);
            newTextFile.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(newTextFile);
            fileWriter.write(aboutInstrumentToSave);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        instrument1.setAboutInstrument(fileDirWithName);
        instrumentService.save(instrument1);

        return ResponseEntity.ok().body(instrument1);

    }

    @PutMapping("/admin/instrument")
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Instrument instrument) {
        Optional<Instrument> instrument1 = instrumentService.getById(instrument.getId());
        if (Objects.nonNull(instrument1)) {

            String dir = System.getProperty("user.dir");
            dir = dir.concat("\\AboutInstrument\\");

            String fileDirWithName = instrument1.get().getAboutInstrument();

            try{
                File newTextFile = new File(fileDirWithName);
                FileWriter fileWriter = new FileWriter(newTextFile);
                fileWriter.write(instrument.getAboutInstrument());
                fileWriter.close();
            } catch (IOException e) {
                System.out.println(e);
            }

            instrument.setAboutInstrument(fileDirWithName);

            instrumentService.save(instrument);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/admin/instrument/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Instrument instrument = instrumentService.getById(id).orElse(null);
        if(instrument != null) {
            Iterable<ArtistInstrument> allArtistsWithInstrument = artistInstrumentService.getByInstrumentId(instrument.getId());
            Iterable<SongInstrument> allSongsWithInstrument = songInstrumentService.getByInstrumentId(instrument.getId());
            for (ArtistInstrument artistInstrument : allArtistsWithInstrument){
                artistInstrumentService.delete(artistInstrument.getId());
            }
            for (SongInstrument songInstrument : allSongsWithInstrument) {
                songInstrumentService.delete(songInstrument.getId());
            }

            File file = new File(instrument.getAboutInstrument());

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

            instrumentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
