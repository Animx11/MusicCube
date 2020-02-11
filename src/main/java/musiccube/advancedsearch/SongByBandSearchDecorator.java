package musiccube.advancedsearch;

public class SongByBandSearchDecorator extends AbstractAdvancedSearchWithParams {
    SongByBandSearchDecorator(AbstractAdvancedSearch search, String titles) {
        super(search,titles);
    }
    SongByBandSearchDecorator(AbstractAdvancedSearch search, boolean negated, String titles) {
        super(search,negated,titles);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + NameInQueryBuilder.build(
                "s.band.bandName",
                paramValues,
                negated
        );
    }
}
