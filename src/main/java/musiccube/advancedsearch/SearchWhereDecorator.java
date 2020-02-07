package musiccube.advancedsearch;

public class SearchWhereDecorator extends AbstractAdvancedSearch {
    protected SearchWhereDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + "WHERE ";
    }
}
