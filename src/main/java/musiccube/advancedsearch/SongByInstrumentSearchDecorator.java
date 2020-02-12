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
        StringBuilder sb = new StringBuilder("((s.id IN (SELECT sa.song.id FROM SongAuthorship sa WHERE  )");
        sb.append(NameInQueryBuilder.build("sa.instrument.instrumentName",paramValues,negated));
        return decorated.generateQuery() + sb.toString();
    }
}
