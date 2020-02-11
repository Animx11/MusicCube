package musiccube.advancedsearch;

class SearchAndDecorator extends AbstractAdvancedSearch {
    SearchAndDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + "AND ";
    }
}
