package musiccube.advancedsearch;

public class BeginSongSearchDecorator extends AbstractAdvancedSearch {
    public BeginSongSearchDecorator() {
        super(null);
    }

    @Override
    String generateQuery() {
        return "SELECT s FROM Song s ";
    }
}
