package musiccube.advancedsearchv2;

import java.util.HashMap;
import java.util.Map;

public class SongParamInterpreter extends AbstractParamInterpreter {

    public SongParamInterpreter(Map<String,String> params) {
        super(params);
        search = new BeginSongSearchDecorator();
        paramsCount = params.size();
    }

    @Override
    public StringBuilder getQuery() {
        if (! params.isEmpty()) {
            search = new WhereSearchDecorator(search);
        }
        if (params.containsKey("title")) {
            search = new SongByTitleSearchDecorator(search,params.get("title"));
        }
        if (params.containsKey("notitle")) {
        }
        if (params.containsKey("minlength")) {
        }
        if (params.containsKey("maxlength")) {
        }
        if (params.containsKey("band")) {
        }
        if (params.containsKey("noband")) {
        }
        if (params.containsKey("genre")) {
        }
        if (params.containsKey("nogenre")) {
        }
        if (params.containsKey("instrument")) {
        }
        if (params.containsKey("noinstrument")) {
        }

        return search.getQuery();
    }

    @Override
    public HashMap getQueryParams() {
        return search.getQueryParams();
    }
}
