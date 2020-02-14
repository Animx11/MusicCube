package musiccube.advancedsearchv2;

class SongByTitleSearchDecorator extends AbstractAdvancedSearchWithParams {
    SongByTitleSearchDecorator(AbstractAdvancedSearch decorated, boolean negated, String paramVals) {
        super(decorated, negated, paramVals);
    }
    SongByTitleSearchDecorator(AbstractAdvancedSearch decorated, String paramVals) {
        super(decorated, paramVals);
    }

    @Override
    StringBuilder getQuery() {
        StringBuilder builder = decorated.getQuery().append("(");
        NameInQueryBuilder.build(builder,queryParams,paramValues,"title","s.songName");
        builder.append(") ");
        return builder;
    }
}
