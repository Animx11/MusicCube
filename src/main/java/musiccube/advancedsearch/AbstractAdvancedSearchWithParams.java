package musiccube.advancedsearch;

public abstract class AbstractAdvancedSearchWithParams extends AbstractAdvancedSearch {
    protected String paramValues;
    protected AbstractAdvancedSearchWithParams(AbstractAdvancedSearch search, String paramVals) {
        super(search);
        this.paramValues = paramVals;
    }
    protected AbstractAdvancedSearchWithParams(AbstractAdvancedSearch decorated, boolean negated, String paramVals) {
        super(decorated, negated);
        this.paramValues = paramVals;
    }
}
