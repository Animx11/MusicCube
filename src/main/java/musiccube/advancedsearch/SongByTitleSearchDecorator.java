package musiccube.advancedsearch;

public class SongByTitleSearchDecorator extends AbstractAdvancedSearch {
    private String songTitles;
    public SongByTitleSearchDecorator(AbstractAdvancedSearch search, String titles) {
        super(search);
        songTitles = titles;
    }
    public SongByTitleSearchDecorator(AbstractAdvancedSearch search, boolean negated, String titles) {
        super(search,negated);
        songTitles = titles;
    }

    @Override
    String generateQuery() {
        String[] titlesArr = songTitles.split(",");
        String query = "LOWER(s.songName) ";
        if (negated) {
            query += "NOT ";
        }
        query += "IN( ";
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
