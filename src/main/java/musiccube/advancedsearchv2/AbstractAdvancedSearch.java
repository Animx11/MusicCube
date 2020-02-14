package musiccube.advancedsearchv2;

import java.util.HashMap;

abstract class AbstractAdvancedSearch {
    protected final AbstractAdvancedSearch decorated;
    protected boolean negated;
    protected HashMap queryParams;
    protected AbstractAdvancedSearch(AbstractAdvancedSearch decorated) {
        this.decorated = decorated;
        if (decorated != null) {
            this.queryParams = decorated.getQueryParams();
        }
    }
    protected AbstractAdvancedSearch(AbstractAdvancedSearch decorated, boolean negated) {
        this.decorated = decorated;
        this.negated = negated;
        if (decorated != null) {
            this.queryParams = decorated.getQueryParams();
        }
    }

    abstract StringBuilder getQuery();
    public HashMap getQueryParams() {
        return queryParams;
    }
}