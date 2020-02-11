package musiccube.advancedsearch;

class BeginBandSearchDecorator extends AbstractAdvancedSearch {
    protected BeginBandSearchDecorator(AbstractAdvancedSearch decorated) {
        super(null);
    }

    @Override
    String generateQuery() {
        return "SELECT b FROM Band b ";
    }
}
