package musiccube.advancedsearchv2;

class BandByGenreSearchDecorator extends NameInSearchDecorator {
    BandByGenreSearchDecorator(AbstractAdvancedSearch decorated, String paramVals, String nameRoot, String propName) {
        super(decorated, false, paramVals, nameRoot, propName);
    }

    @Override
    StringBuilder getQuery() {
        StringBuilder builder = decorated.getQuery();
        builder.append("b.id IN (SELECT s.band.id FROM Song s WHERE (");
        NameInQueryBuilder.build(this,builder);
        builder.append(") ) ");

        return builder;
    }
}
