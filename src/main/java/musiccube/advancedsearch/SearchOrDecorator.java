package musiccube.advancedsearch;

public class SearchOrDecorator extends AbstractAdvancedSearch {
    protected SearchOrDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + "OR ";
    }
}
