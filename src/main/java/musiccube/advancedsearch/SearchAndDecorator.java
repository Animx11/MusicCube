package musiccube.advancedsearch;

public class SearchAndDecorator extends AbstractAdvancedSearch {
    protected SearchAndDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + "AND ";
    }
}
