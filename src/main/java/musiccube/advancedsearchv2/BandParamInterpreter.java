package musiccube.advancedsearchv2;

import java.util.HashMap;
import java.util.Map;

public class BandParamInterpreter extends AbstractParamInterpreter {
    public BandParamInterpreter(Map<String, String> params) {
        super(params);
        search = new BeginBandSearchDecorator();
        paramsCount = params.size();
        factory = new SearchDecoratorFactory();
    }
    @Override
    public StringBuilder getQuery() {
        if (! params.isEmpty()) {
            search = new WhereSearchDecorator(search);
        }
        if (params.containsKey("name")) {
            search = factory.getDecorator(search,params.get("name"),factory.BAND_NAME);
            and();
        }
        if (params.containsKey("noname")) {
            search = factory.getDecorator(search,params.get("noname"),factory.BAND_NO_NAME);
        }
        if (params.containsKey("formedafter")) {
            search = new BandByDateSearchDecorator(search,params.get("formedafter"),true);
        }
        if (params.containsKey("formedbefore")) {
            search = new BandByDateSearchDecorator(search,params.get("formedbefore"),false);
        }
        if (params.containsKey("country")) {
            search = factory.getDecorator(search,params.get("country"),factory.BAND_COUNTRY);
        }
        if (params.containsKey("nocountry")) {
            search = factory.getDecorator(search,params.get("nocountry"),factory.BAND_NO_COUNTRY);
        }
        if (params.containsKey("genre")) {
            search = factory.getDecorator(search,params.get("genre"),factory.BAND_BY_GENRE);
        }

        return search.getQuery();
    }

    @Override
    public HashMap getQueryParams() {
        return search.getQueryParams();
    }
}
