package musiccube.advancedsearchv2;

import java.util.HashMap;
import java.util.Map;

public class SongParamInterpreter extends AbstractParamInterpreter {
    private NameInSearchDecoratorFactory factory;

    public SongParamInterpreter(Map<String,String> params) {
        super(params);
        search = new BeginSongSearchDecorator();
        paramsCount = params.size();
        factory = new NameInSearchDecoratorFactory();
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
        if (params.containsKey("minlength")) {
            and();
        }
        if (params.containsKey("maxlength")) {
            and();
        }
        if (params.containsKey("band")) {
            search = factory.getDecorator(search,params.get("band"),factory.SONG_BAND);
            and();
        }
        if (params.containsKey("noband")) {
            search = factory.getDecorator(search,params.get("band"),factory.SONG_NO_BAND);
            and();
        }
        if (params.containsKey("genre")) {
            and();
        }
        if (params.containsKey("nogenre")) {
            and();
        }
        if (params.containsKey("instrument")) {
            and();
        }
        if (params.containsKey("noinstrument")) {
            and();
        }

        return search.getQuery();
    }

    @Override
    public HashMap getQueryParams() {
        return search.getQueryParams();
    }
}
