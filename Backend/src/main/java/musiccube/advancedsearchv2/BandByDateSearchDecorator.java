package musiccube.advancedsearchv2;

import java.util.Calendar;

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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,Integer.parseInt(paramValues));
        if (searchAfter) {
            builder.append(">= :bandAfterDate ) ");
            queryParams.put("bandAfterDate",calendar.getTime());
        } else {
            builder.append("<= :bandBeforeDate ) ");
            queryParams.put("bandBeforeDate",calendar.getTime());
        }
        return builder;
    }
}
