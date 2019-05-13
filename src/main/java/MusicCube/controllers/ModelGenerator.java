package MusicCube.controllers;

import MusicCube.entities.*;
import MusicCube.services.album.AlbumService;
import MusicCube.services.artist.ArtistService;
import MusicCube.services.artistinband.ArtistInBandService;
import MusicCube.services.artistinstrument.ArtistInstrumentService;
import MusicCube.services.band.BandService;
import MusicCube.services.genre.GenreService;
import MusicCube.services.genreinstrument.GenreInstrumentService;
import MusicCube.services.instrument.InstrumentService;
import MusicCube.services.localization.LocalizationService;
import MusicCube.services.song.SongService;
import MusicCube.services.songauthorship.SongAuthorshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="http://localhost:4200")
public class ModelGenerator {

    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private BandService bandService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistInBandService artistInBandService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private GenreInstrumentService genreInstrumentService;
    @Autowired
    private InstrumentService instrumentService;
    @Autowired
    private SongAuthorshipService songAuthorshipService;
    @Autowired
    private SongService songService;
    @Autowired
    private ArtistInstrumentService artistInstrumentService;

    @RequestMapping(value = "generateModel", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateModel() {

        Localization birmingham = new Localization("England","Birmingham","");
        Localization uk = new Localization("United Kingdom","","");
        Localization wolver = new Localization("England","Wolverhampton","");
        Band judas = new Band("Judas Priest",new Date(70,01,01),birmingham);

        Album britishSteelAlbum = new Album("Brithis Steel",
                2162,new Date(80,4,14),
                "CBS");

        Artist halford = new Artist(birmingham,"Robert John Arthur","Halford",new Date(51,8,25),"Rob Halford");
        Artist kk = new Artist(birmingham,"Kenneth","Downing",new Date(51,10,27),"K.K. Downing");
        Artist glen = new Artist(birmingham,"Glenn Raymond","Tipton",new Date(47,10,25),"Glenn Tipton");
        Artist hill = new Artist(birmingham,"Ian Frank","Hill",new Date(51,1,20),"Ian Hill");
        Artist holland = new Artist(wolver,"David","Holland",new Date(48,5,4),"Dave Holland");

        ArtistInBand halfordPriest = new ArtistInBand(halford,judas,"273-292, 2003-present",true,"Vocals");
        ArtistInBand kkPriest = new ArtistInBand(kk,judas,"270-2011",false,"Guitars");
        ArtistInBand glenPriest = new ArtistInBand(glen,judas,"274-present",true,"Guitars");
        ArtistInBand hillPriest = new ArtistInBand(hill,judas,"270-present",true,"Bass");
        ArtistInBand hollandPriest = new ArtistInBand(holland,judas,"279-289",false,"Drums");

        Instrument electricGuitar = new Instrument("Electric Guitar","String");
        Instrument bassGuitar = new Instrument("Bass guitar","String");
        Instrument drums = new Instrument("Drums","Percussion");

        ArtistInstrument kkIns = new ArtistInstrument(kk,electricGuitar);
        ArtistInstrument glenIns = new ArtistInstrument(glen,electricGuitar);
        ArtistInstrument hillIns = new ArtistInstrument(hill,bassGuitar);
        ArtistInstrument hollIns = new ArtistInstrument(holland,drums);

        Genre heavy = new Genre(uk,"Heavy Metal",new Date(70,1,1));
        GenreInstrument hmGuitar = new GenreInstrument(heavy,electricGuitar);
        GenreInstrument hmBass = new GenreInstrument(heavy,bassGuitar);
        GenreInstrument hmDrums = new GenreInstrument(heavy,drums);

        Song rapid = new Song("Rapid Fire",246,britishSteelAlbum,judas,heavy);
        Song mgods = new Song("Metal Gods",241,britishSteelAlbum,judas,heavy);
        Song breaking = new Song("Breaking The Law",155,britishSteelAlbum,judas,heavy);
        Song grinder = new Song("Grinder",237,britishSteelAlbum,judas,heavy);
        Song united = new Song("United",214,britishSteelAlbum,judas,heavy);
        Song wise = new Song("You Don't Have to Be Old to Be Wise",304,britishSteelAlbum,judas,heavy);
        Song midnight = new Song("Living After Midnight",211,britishSteelAlbum,judas,heavy);
        Song rage = new Song("The Rage",285,britishSteelAlbum,judas,heavy);
        Song steeler = new Song("Steeler",269,britishSteelAlbum,judas,heavy);
        
        SongAuthorship rapidA = new SongAuthorship(true,false,rapid,halford);
        SongAuthorship mgodsA = new SongAuthorship(true,false,mgods,halford);
        SongAuthorship breakingA = new SongAuthorship(true,false,breaking,halford);
        SongAuthorship grinderA = new SongAuthorship(true,false,grinder,halford);
        SongAuthorship unitedA = new SongAuthorship(true,false,united,halford);
        SongAuthorship wiseA = new SongAuthorship(true,false,wise,halford);
        SongAuthorship midnightA = new SongAuthorship(true,false,midnight,halford);
        SongAuthorship rageA = new SongAuthorship(true,false,rage,halford);
        SongAuthorship steelerA = new SongAuthorship(true,false,steeler,halford);
                
        SongAuthorship rapidA1 = new SongAuthorship(false,true,rapid,glen);
        SongAuthorship mgodsA1 = new SongAuthorship(false,true,mgods,glen);
        SongAuthorship breakingA1 = new SongAuthorship(false,true,breaking,glen);
        SongAuthorship grinderA1 = new SongAuthorship(false,true,grinder,glen);
        SongAuthorship unitedA1 = new SongAuthorship(false,true,united,glen);
        SongAuthorship wiseA1 = new SongAuthorship(false,true,wise,glen);
        SongAuthorship midnightA1 = new SongAuthorship(false,true,midnight,glen);
        SongAuthorship rageA1 = new SongAuthorship(false,true,rage,glen);
        SongAuthorship steelerA1 = new SongAuthorship(false,true,steeler,glen);

        SongAuthorship rapidA2 = new SongAuthorship(false,true,rapid,kk);
        SongAuthorship mgodsA2 = new SongAuthorship(false,true,mgods,kk);
        SongAuthorship breakingA2 = new SongAuthorship(false,true,breaking,kk);
        SongAuthorship grinderA2 = new SongAuthorship(false,true,grinder,kk);
        SongAuthorship unitedA2 = new SongAuthorship(false,true,united,kk);
        SongAuthorship wiseA2 = new SongAuthorship(false,true,wise,kk);
        SongAuthorship midnightA2 = new SongAuthorship(false,true,midnight,kk);
        SongAuthorship rageA2 = new SongAuthorship(false,true,rage,kk);
        SongAuthorship steelerA2 = new SongAuthorship(false,true,steeler,kk);

        localizationService.save(birmingham);
        localizationService.save(uk);
        localizationService.save(wolver);

        bandService.save(judas);

        albumService.save(britishSteelAlbum);

        artistService.save(halford);
        artistService.save(kk);
        artistService.save(glen);
        artistService.save(hill);
        artistService.save(holland);

        artistInBandService.save(halfordPriest);
        artistInBandService.save(kkPriest);
        artistInBandService.save(glenPriest);
        artistInBandService.save(hillPriest);
        artistInBandService.save(hollandPriest);

        instrumentService.save(electricGuitar);
        instrumentService.save(bassGuitar);
        instrumentService.save(drums);

        artistInstrumentService.save(kkIns);
        artistInstrumentService.save(glenIns);
        artistInstrumentService.save(hillIns);
        artistInstrumentService.save(hollIns);

        genreService.save(heavy);

        genreInstrumentService.save(hmBass);
        genreInstrumentService.save(hmDrums);
        genreInstrumentService.save(hmGuitar);

        songService.save(rage);
        songService.save(rapid);
        songService.save(mgods);
        songService.save(midnight);
        songService.save(breaking);
        songService.save(grinder);
        songService.save(united);
        songService.save(steeler);
        songService.save(wise);

        songAuthorshipService.save(wiseA);
        songAuthorshipService.save(wiseA1);
        songAuthorshipService.save(wiseA2);
        songAuthorshipService.save(rageA);
        songAuthorshipService.save(rageA1);
        songAuthorshipService.save(rageA2);
        songAuthorshipService.save(rapidA);
        songAuthorshipService.save(rapidA1);
        songAuthorshipService.save(rapidA2);
        songAuthorshipService.save(breakingA);
        songAuthorshipService.save(breakingA1);
        songAuthorshipService.save(breakingA2);
        songAuthorshipService.save(unitedA);
        songAuthorshipService.save(unitedA1);
        songAuthorshipService.save(unitedA2);
        songAuthorshipService.save(mgodsA);
        songAuthorshipService.save(mgodsA1);
        songAuthorshipService.save(mgodsA2);
        songAuthorshipService.save(wiseA);
        songAuthorshipService.save(wiseA1);
        songAuthorshipService.save(wiseA2);
        songAuthorshipService.save(midnightA);
        songAuthorshipService.save(midnightA1);
        songAuthorshipService.save(midnightA2);
        songAuthorshipService.save(grinderA);
        songAuthorshipService.save(grinderA1);
        songAuthorshipService.save(grinderA2);
        songAuthorshipService.save(steelerA);
        songAuthorshipService.save(steelerA1);
        songAuthorshipService.save(steelerA2);

        return "Model generated.";
    }
}
