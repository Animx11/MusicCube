package musiccube.advancedsearchv2;

import java.util.HashMap;
import java.util.Map;

public class SongParamInterpreter extends AbstractParamInterpreter {

    public SongParamInterpreter(Map<String,String> params) {
        super(params);
        search = new BeginSongSearchDecorator();
        paramsCount = params.size();
        factory = new SearchDecoratorFactory();
    }

    @Override
    public StringBuilder getQuery() {
        if (! params.isEmpty()) {
            search = new WhereSearchDecorator(search);
        }
        if (params.containsKey("title")) {
            search = factory.getDecorator(search,params.get("title"),factory.SONG_TITLE);
            and();
        }
        if (params.containsKey("notitle")) {
            search = factory.getDecorator(search,params.get("notitle"),factory.SONG_NO_TITLE);
            and();
        }
        if (params.containsKey("minlen")) {
            search = factory.getDecorator(search,params.get("minlen"),factory.SONG_MIN);
            and();
        }
        if (params.containsKey("maxlen")) {
            search = factory.getDecorator(search,params.get("maxlen"),factory.SONG_MAX);
            and();
        }
        if (params.containsKey("band")) {
            search = factory.getDecorator(search,params.get("band"),factory.SONG_BAND);
            and();
        }
        if (params.containsKey("noband")) {
            search = factory.getDecorator(search,params.get("noband"),factory.SONG_NO_BAND);
            and();
        }
        if (params.containsKey("genre")) {
            search = factory.getDecorator(search,params.get("genre"),factory.SONG_GENRE);
            and();
        }
        if (params.containsKey("nogenre")) {
            search = factory.getDecorator(search,params.get("nogenre"),factory.SONG_NO_GENRE);
            and();
        }
        if (params.containsKey("instrument")) {
            search = factory.getDecorator(search,params.get("instrument"),factory.SONG_INSTR);
            and();
        }
        if (params.containsKey("noinstrument")) {
            search = factory.getDecorator(search,params.get("noinstrument"),factory.SONG_NO_INSTR);
            and();
        }

        return search.getQuery();
    }

    @Override
    public HashMap getQueryParams() {
        return search.getQueryParams();
    }
}
