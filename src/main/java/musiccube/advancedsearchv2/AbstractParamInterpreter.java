package musiccube.advancedsearchv2;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractParamInterpreter {
    protected Map<String,String> params;
    protected int paramsCount;
    protected AbstractAdvancedSearch search;
    protected SearchDecoratorFactory factory;

    protected AbstractParamInterpreter(Map<String,String> params) {
        this.params = params;
    }
    abstract StringBuilder getQuery();
    abstract HashMap getQueryParams();
    protected void and() {
        if (--paramsCount > 0) {
            search = new AndSearchDecorator(search);
        }
    }
}
