package musiccube.advancedsearch;

class SongByMinLengthSearchDecorator extends AbstractAdvancedSearch {
    private int lengthInSeconds;
    SongByMinLengthSearchDecorator(AbstractAdvancedSearch search, String minlength) {
        super(search);
        lengthInSeconds = Integer.parseInt(minlength);
    }

    @Override
    String generateQuery() {
        return decorated.generateQuery() + "(s.songLengthSeconds >= "+lengthInSeconds+") ";
    }
}
