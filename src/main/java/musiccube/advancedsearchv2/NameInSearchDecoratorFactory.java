package musiccube.advancedsearchv2;

class NameInSearchDecoratorFactory {
    String SONG_TITLE = "songByTitle";
    String SONG_NO_TITLE = "songByNoTitle";
    String SONG_BAND = "songByBand";
    String SONG_NO_BAND = "songByNoBand";

    NameInSearchDecorator getDecorator(AbstractAdvancedSearch decorated, String paramVals, String option) {
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
        return new NameInSearchDecorator(decorated,false,paramVals,"","");
    }
}
