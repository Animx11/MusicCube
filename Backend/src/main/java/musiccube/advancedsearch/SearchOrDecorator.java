package musiccube.advancedsearch;

class SearchOrDecorator extends AbstractAdvancedSearch {
    SearchOrDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + "OR ";
    }
}
