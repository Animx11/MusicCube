package musiccube.advancedsearchv2;

class WhereSearchDecorator extends AbstractAdvancedSearch {
    WhereSearchDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    StringBuilder getQuery() {
        return decorated.getQuery().append("WHERE ");
    }
}
