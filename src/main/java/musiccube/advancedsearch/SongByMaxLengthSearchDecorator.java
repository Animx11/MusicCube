package musiccube.advancedsearch;

public class SongByMaxLengthSearchDecorator extends AbstractAdvancedSearch {
    public SongByMaxLengthSearchDecorator(AbstractAdvancedSearch search, String minlength) {
        super(search);
    }

    @Override
    String generateQuery() {
        return null;
    }
}
