package musiccube.advancedsearch;

public class SongByMaxLengthSearchDecorator extends AbstractAdvancedSearch {
    private int length;
    SongByMaxLengthSearchDecorator(AbstractAdvancedSearch search, String minlength) {
        super(search);
        this.length = Integer.parseInt(minlength);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + "s.songLengthSeconds < " + length + " ";
    }
}
