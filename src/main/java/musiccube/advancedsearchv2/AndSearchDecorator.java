package musiccube.advancedsearchv2;

public class AndSearchDecorator extends AbstractAdvancedSearch {
    AndSearchDecorator(AbstractAdvancedSearch decorated) {
        super(decorated);
    }

    @Override
    StringBuilder getQuery() {
        return decorated.getQuery().append("AND ");
    }
}
