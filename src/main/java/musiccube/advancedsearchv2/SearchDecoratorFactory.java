package musiccube.advancedsearchv2;

class SearchDecoratorFactory {
    static final String SONG_NO_INSTR = "songByNoInstr";
    static final String SONG_INSTR = "songByInstr";
    static final String SONG_MAX = "songByMaxLen";
    static final String SONG_MIN = "songByMinLen";
    static final String SONG_GENRE = "songByGenre";
    static final String SONG_NO_GENRE = "songByNoGenre";
    static final String SONG_TITLE = "songByTitle";
    static final String SONG_NO_TITLE = "songByNoTitle";
    static final String SONG_BAND = "songByBand";
    static final String SONG_NO_BAND = "songByNoBand";

    AbstractAdvancedSearch getDecorator(AbstractAdvancedSearch decorated, String paramVals, String option) {
        if (option.equals(SONG_TITLE)) {
            return new NameInSearchDecorator(decorated,false,paramVals,"title","s.songName");
        }
        if (option.equals(SONG_NO_TITLE)) {
            return new NameInSearchDecorator(decorated,true,paramVals,"notitle","s.songName");
        }
        if (option.equals(SONG_BAND)) {
            return new NameInSearchDecorator(decorated,false,paramVals,"band","s.band.bandName");
        }
        if (option.equals(SONG_NO_BAND)) {
            return new NameInSearchDecorator(decorated,true,paramVals,"noband","s.band.bandName");
        }
        if (option.equals(SONG_GENRE)) {
            return new NameInSearchDecorator(decorated,false,paramVals,"genre","s.genre.genreName");
        }
        if (option.equals(SONG_NO_GENRE)) {
            return new NameInSearchDecorator(decorated,true,paramVals,"nogenre","s.genre.genreName");
        }
        if (option.equals(SONG_INSTR)) {

        }
        if (option.equals(SONG_NO_INSTR)) {

        }
        if (option.equals(SONG_MAX)) {
            return new SongByLengthSearchDecorator(decorated,paramVals,"MAX");
        }
        if (option.equals(SONG_MIN)) {
            return new SongByLengthSearchDecorator(decorated,paramVals,"MIN");

        }
        return new NameInSearchDecorator(decorated,false,paramVals,"","");
    }
}
