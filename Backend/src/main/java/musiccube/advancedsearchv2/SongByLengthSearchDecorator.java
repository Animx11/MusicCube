package musiccube.advancedsearchv2;

class SongByLengthSearchDecorator extends AbstractAdvancedSearch {
    private int len;
    private String minmax;
    SongByLengthSearchDecorator(AbstractAdvancedSearch decorated, String lenStr, String minmax) {
        super(decorated);
        len = Integer.parseInt(lenStr);
        this.minmax = minmax;
    }

    @Override
    StringBuilder getQuery() {
        if (minmax.equals("MAX")) {
            queryParams.put("songmaxlen",len);
            return decorated.getQuery().append("(s.songLengthSeconds <= :songmaxlen ) ");
        }
        if (minmax.equals("MIN")) {
            queryParams.put("songminlen",len);
            return decorated.getQuery().append("(s.songLengthSeconds >= :songminlen ) ");

        }
        return decorated.getQuery();
    }
}
