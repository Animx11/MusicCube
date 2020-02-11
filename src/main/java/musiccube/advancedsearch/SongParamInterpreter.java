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
            search = new SongByTitleSearchDecorator(search,params.get("title"));
            and();
        }
        if (params.containsKey("notitle")) {
            search = new SongByTitleSearchDecorator(search,true,params.get("notitle"));
            and();
        }
        if (params.containsKey("minlength")) {
            search = new SongByMinLengthSearchDecorator(search,params.get("minlength"));
            and();
        }
        if (params.containsKey("maxlength")) {
            search = new SongByMaxLengthSearchDecorator(search,params.get("maxlength"));
            and();
        }
        if (params.containsKey("band")) {
            search = new SongByBandSearchDecorator(search,params.get("band"));
            and();
        }
        if (params.containsKey("noband")) {
            search = new SongByBandSearchDecorator(search,true,params.get("noband"));
            and();
        }
        if (params.containsKey("genre")) {
            search = new SongByGenreSearchDecorator(search,params.get("genre"));
            and();
        }
        if (params.containsKey("nogenre")) {
            search = new SongByGenreSearchDecorator(search,true,params.get("nogenre"));
            and();
        }
        if (params.containsKey("instrument")) {
            search = new SongByInstrumentSearchDecorator(search,params.get("instrument"));
            and();
        }
        if (params.containsKey("noinstrument")) {
            search = new SongByInstrumentSearchDecorator(search,true,params.get("noinstrument"));
            and();
        }

        return search.generateQuery();
    }

}
