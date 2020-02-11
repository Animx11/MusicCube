package musiccube.advancedsearch;

public class SongByGenreSearchDecorator extends AbstractAdvancedSearchWithParams {
    SongByGenreSearchDecorator(AbstractAdvancedSearch search, String params) {
        super(search,params);
    }
    SongByGenreSearchDecorator(AbstractAdvancedSearch search, boolean negated, String params) {
        super(search,negated,params);
    }

    @Override
    String generateQuery() {
        return null;
    }
}
