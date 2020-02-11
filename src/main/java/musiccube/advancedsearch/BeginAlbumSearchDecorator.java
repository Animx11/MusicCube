package musiccube.advancedsearch;

class BeginAlbumSearchDecorator extends AbstractAdvancedSearch {
    protected BeginAlbumSearchDecorator(AbstractAdvancedSearch decorated) {
        super(null);
    }

    @Override
    String generateQuery() {
        return "SELECT a FROM Album a";
    }
}
