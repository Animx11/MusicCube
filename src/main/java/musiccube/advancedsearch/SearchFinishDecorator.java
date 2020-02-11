package musiccube.advancedsearch;

class SearchFinishDecorator extends AbstractAdvancedSearch {
    SearchFinishDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + ") ";
    }
}
