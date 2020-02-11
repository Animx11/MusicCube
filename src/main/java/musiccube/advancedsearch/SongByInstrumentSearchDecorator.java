package musiccube.advancedsearch;

class SongByInstrumentSearchDecorator extends AbstractAdvancedSearchWithParams {
    SongByInstrumentSearchDecorator(AbstractAdvancedSearch search, String instruments) {
        super(search,instruments);
    }
    SongByInstrumentSearchDecorator(AbstractAdvancedSearch decorated, boolean negated, String paramVals) {
        super(decorated, negated, paramVals);
    }

    @Override
    String generateQuery() {
        return null;
    }
}
