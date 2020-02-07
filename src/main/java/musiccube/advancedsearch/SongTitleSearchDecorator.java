package musiccube.advancedsearch;

public class SongTitleSearchDecorator extends AbstractAdvancedSearch {
    private String songTitles;
    public SongTitleSearchDecorator(AbstractAdvancedSearch search, String s) {
        super(search);
        songTitles = s;
    }

    @Override
    String generateQuery() {
        String[] titlesArr = songTitles.split(",");
        String query = "LOWER(s.songName) IN (";
    }
}
