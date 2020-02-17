package musiccube.advancedsearch;

abstract class AbstractAdvancedSearch {
    protected final AbstractAdvancedSearch decorated;
    protected boolean negated;
    protected AbstractAdvancedSearch(AbstractAdvancedSearch decorated) {
        this.decorated = decorated;
    }
    protected AbstractAdvancedSearch(AbstractAdvancedSearch decorated, boolean negated) {
        this.decorated = decorated;
        this.negated = negated;
    }

    abstract String generateQuery();
}
