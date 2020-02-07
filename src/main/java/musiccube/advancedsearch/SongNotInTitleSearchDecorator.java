package musiccube.advancedsearch;

public class SongNotInTitleSearchDecorator extends AbstractAdvancedSearch {
    private String songTitles;
    public SongNotInTitleSearchDecorator(AbstractAdvancedSearch search, String titles) {
        super(search);
        songTitles = titles;
    }

    @Override
    String generateQuery() {
        String[] titlesArr = songTitles.split(",");
        String query = "LOWER(s.songName) NOT IN( ";
        int len = titlesArr.length;
        for (int i = 0; i < len; i++) {
            query += ("LOWER(" + titlesArr[i] + ")");
            if (i < len-1) {
                query += ",";
            }
            query += " ";
        }

        return decorated.generateQuery() + query;
    }
}
