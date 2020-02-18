package musiccube.advancedsearchv2;

public abstract class AbstractAdvancedSearchWithParams extends AbstractAdvancedSearch {
    protected String paramValues;
    protected AbstractAdvancedSearchWithParams(AbstractAdvancedSearch decorated, String paramVals) {
        super(decorated);
        this.paramValues = paramVals;
    }
    protected AbstractAdvancedSearchWithParams(AbstractAdvancedSearch decorated, boolean negated, String paramVals) {
        super(decorated, negated);
        this.paramValues = paramVals;
    }
}
