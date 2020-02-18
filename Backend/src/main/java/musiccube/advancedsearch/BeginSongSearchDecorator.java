package musiccube.advancedsearch;

class BeginSongSearchDecorator extends AbstractAdvancedSearch {
    BeginSongSearchDecorator() {
        super(null);
    }

    @Override
    String generateQuery() {
        return "SELECT s FROM Song s ";
    }
}
