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
        String query = "LOWER(s.songName) IN( ";
        int len = titlesArr.length;
        for (int i = 0; i < len; i++) {
            query += ("LOWER(" + titlesArr[i] + ")");
            if (i < len-1) {
                query += ",";
            }
            query += " ";
        }
        query += ") ";

        return decorated.generateQuery() + query;
    }
}
