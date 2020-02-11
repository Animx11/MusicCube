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
        return decorated.generateQuery() + NameInQueryBuilder.build(
                "s.songName",
                paramValues,
                negated
        );
    }
}
