package musiccube.advancedsearch;

import java.util.Arrays;
import java.util.Map;

public class SongParamInterpreter extends AbstractParamInterpreter {
    private AbstractAdvancedSearch search;
    public SongParamInterpreter(Map<String, String> params) {
        super(params);
    }

    @Override
    String getQuery() {
        search = new BeginSongSearchDecorator();
        if (! params.isEmpty()) {
            search = new SearchWhereDecorator(search);
        }
        if (params.containsKey("title")) {
            search = new SongTitleSearchDecorator(search,params.get("title"));
        }




        return search.generateQuery();
    }

}
