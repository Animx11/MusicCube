package musiccube.advancedsearch;

import java.util.Map;

public abstract class AbstractParamInterpreter {
    protected AbstractAdvancedSearch search;
    protected Map<String,String> params;
    protected int paramsCount;
    protected AbstractParamInterpreter(Map<String,String> params) {
        this.params = params;
    }
    abstract String getQuery();
    protected void and() {
        if (--paramsCount > 0) {
            search = new SearchAndDecorator(search);
        } else {
            search = new SearchFinishDecorator(search);
        }
    }
}
