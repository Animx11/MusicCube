package musiccube.advancedsearchv2;

import java.util.HashMap;

class BeginBandSearchDecorator extends AbstractAdvancedSearch {
    BeginBandSearchDecorator() {
        super(null);
        this.queryParams = new HashMap();
    }

    @Override
    StringBuilder getQuery() {
        return new StringBuilder("SELECT b FROM Band b ");
    }
}
