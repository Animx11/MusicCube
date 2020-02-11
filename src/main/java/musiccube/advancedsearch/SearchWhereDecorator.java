package musiccube.advancedsearch;

class SearchWhereDecorator extends AbstractAdvancedSearch {
    SearchWhereDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + "WHERE (";
    }
}
