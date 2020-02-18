package musiccube.advancedsearchv2;

class SongByInstrumentSearchDecorator extends NameInSearchDecorator {

    SongByInstrumentSearchDecorator(AbstractAdvancedSearch decorated, boolean negated, String paramVals, String nameRoot, String propName) {
        super(decorated, negated, paramVals, nameRoot, propName);
    }
    @Override
    StringBuilder getQuery() {
        StringBuilder builder = decorated.getQuery();
        builder.append("s.id IN (SELECT si.song.id FROM SongInstrument si WHERE (");
        NameInQueryBuilder.build(this,builder);
        builder.append(") ) ");

        return builder;
    }
}
