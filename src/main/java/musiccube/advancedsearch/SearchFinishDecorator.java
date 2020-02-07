package musiccube.advancedsearch;

public class SearchFinishDecorator extends AbstractAdvancedSearch {
    SearchFinishDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + ") ";
    }
}
