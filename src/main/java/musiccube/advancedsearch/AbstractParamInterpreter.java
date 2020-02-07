package musiccube.advancedsearch;

import java.util.Map;

public abstract class AbstractParamInterpreter {
    protected Map<String,String> params;
    protected AbstractParamInterpreter(Map<String,String> params) {
        this.params = params;
    }
    abstract String getQuery();
}
