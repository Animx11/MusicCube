package musiccube.advancedsearchv2;

import java.util.HashMap;

public class SongByTitleSearchDecorator extends AbstractAdvancedSearchWithParams {
    protected SongByTitleSearchDecorator(AbstractAdvancedSearch decorated, boolean negated, String paramVals) {
        super(decorated, negated, paramVals);
    }

    @Override
    StringBuilder getQuery() {
        StringBuilder builder = decorated.getQuery().append("(");
        NameInQueryBuilder.build(builder,queryParams,paramValues,"title","s.songName");
        builder.append(") ");
        return builder;
    }
}
