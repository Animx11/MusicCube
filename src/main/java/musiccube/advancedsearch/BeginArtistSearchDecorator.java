package musiccube.advancedsearch;

public class BeginArtistSearchDecorator extends AbstractAdvancedSearch {
    protected BeginArtistSearchDecorator(AbstractAdvancedSearch decorated) {
        super(null);
    }

    @Override
    String generateQuery() {
        return "SELECT a FROM Artist a ";
    }
}
