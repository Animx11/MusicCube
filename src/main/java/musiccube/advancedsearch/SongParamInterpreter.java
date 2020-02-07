package musiccube.advancedsearch;

import java.util.Map;

public class SongParamInterpreter extends AbstractParamInterpreter {
    public SongParamInterpreter(Map<String, String> params) {
        super(params);
    }

    @Override
    String getQuery() {
        search = new BeginSongSearchDecorator();
        paramsCount = params.size();
        if (! params.isEmpty()) {
            search = new SearchWhereDecorator(search);
        }
        if (params.containsKey("title")) {
            search = new SongTitleSearchDecorator(search,params.get("title"));
            and();
        }
        if (params.containsKey("notitle")) {
            search = new SongTitleSearchDecorator(search,true,params.get("notitle"));
            and();
        }

        return search.generateQuery();
    }

}
