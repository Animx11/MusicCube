package musiccube.advancedsearchv2;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractParamInterpreter {
    protected Map<String,String> params;
    protected HashMap queryParams;
    protected int paramsCount;
    protected AbstractAdvancedSearch search;
    protected AbstractParamInterpreter(Map<String,String> params) {
        this.params = params;
        queryParams = new HashMap();
    }
    abstract String getQuery();
    abstract HashMap getQueryParams();
}
