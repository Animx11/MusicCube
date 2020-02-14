package musiccube.advancedsearchv2;

public class SongByBandSearchDecorator extends AbstractAdvancedSearchWithParams {
    SongByBandSearchDecorator(AbstractAdvancedSearch decorated, String paramVals) {
        super(decorated, paramVals);
    }

    SongByBandSearchDecorator(AbstractAdvancedSearch decorated, boolean negated, String paramVals) {
        super(decorated, negated, paramVals);
    }

    @Override
    StringBuilder getQuery() {
        return null;
    }
}
