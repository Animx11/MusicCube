package musiccube.advancedsearchv2;

public class BandByDateSearchDecorator extends AbstractAdvancedSearchWithParams {
    private boolean searchAfter;
    protected BandByDateSearchDecorator(AbstractAdvancedSearch decorated, String paramVals, boolean searchAfter) {
        super(decorated, paramVals);
        this.searchAfter = searchAfter;
    }

    @Override
    StringBuilder getQuery() {
        StringBuilder builder = decorated.getQuery();
        builder.append("(b.creationDate ");
        if (searchAfter) {
            builder.append(">= :bandAfterDate ) ");
            queryParams.put("bandAfterDate",paramValues);
        } else {
            builder.append("<= :bandBeforeDate ) ");
            queryParams.put("bandBeforeDate",paramValues);
        }
        return builder;
    }
}
