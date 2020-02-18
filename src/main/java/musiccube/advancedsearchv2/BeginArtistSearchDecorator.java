package musiccube.advancedsearchv2;

import java.util.HashMap;

public class BeginArtistSearchDecorator extends AbstractAdvancedSearch {
    BeginArtistSearchDecorator(AbstractAdvancedSearch decorated) {
        super(null);
        this.queryParams = new HashMap();
    }

    @Override
    StringBuilder getQuery() {
        return new StringBuilder("SELECT a FROM Artist a ");
    }
}
