package musiccube.advancedsearch;

public class SongByTitleSearchDecorator extends AbstractAdvancedSearchWithParams {
    SongByTitleSearchDecorator(AbstractAdvancedSearch search, String params) {
        super(search,params);
    }
    SongByTitleSearchDecorator(AbstractAdvancedSearch search, boolean negated, String params) {
        super(search,negated,params);
    }

    @Override
    String generateQuery() {
        String[] titlesArr = paramValues.split(",");
        StringBuilder query = new StringBuilder("LOWER(s.songName) ");
        if (negated) {
            query.append("NOT ");
        }
        query.append("IN( ");
        int len = titlesArr.length;
        for (int i = 0; i < len; i++) {
            query.append("LOWER(").append(titlesArr[i]).append(")");
            if (i < len-1) {
                query.append(",");
            }
            query.append(" ");
        }

        return decorated.generateQuery() + query.toString();
    }
}
