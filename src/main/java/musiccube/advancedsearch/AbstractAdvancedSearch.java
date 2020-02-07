package musiccube.advancedsearch;

abstract class AbstractAdvancedSearch {
    protected final AbstractAdvancedSearch decorated;
    protected AbstractAdvancedSearch(AbstractAdvancedSearch decorated) {
        this.decorated = decorated;
    }
    abstract String generateQuery();
}
