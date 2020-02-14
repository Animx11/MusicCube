package musiccube.advancedsearchv2;

import java.util.HashMap;
import java.util.Map;

public class SongParamInterpreter extends AbstractParamInterpreter {

    public SongParamInterpreter(Map<String,String> params) {
        super(params);
    }

    @Override
    public String getQuery() {
        search = new BeginSongSearchDecorator(queryParams);
        paramsCount = params.size();
        if (params.containsKey("title")) {
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

        return "";
    }

    @Override
    HashMap getQueryParams() {
        return queryParams;
    }
}
