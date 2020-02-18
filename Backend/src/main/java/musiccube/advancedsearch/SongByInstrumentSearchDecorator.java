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
        StringBuilder sb = new StringBuilder("(s.id ");
        if (negated) {
            sb.append("NOT ");
        }
        sb.append("IN (SELECT si.song.id FROM SongInstrument si WHERE ")
                .append(NameInQueryBuilder.build("si.instrument.instrumentName",paramValues,false))
                .append(") ) ) ");
        return decorated.generateQuery() + sb.toString();
    }
}
