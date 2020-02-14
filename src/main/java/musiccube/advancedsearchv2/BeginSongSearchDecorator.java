package musiccube.advancedsearchv2;

import java.util.HashMap;

class BeginSongSearchDecorator extends AbstractAdvancedSearch {
    BeginSongSearchDecorator() {
        super(null);
        this.queryParams = new HashMap();
    }

    @Override
    StringBuilder getQuery() {
        return new StringBuilder("SELECT s FROM Song s ");
    }
}
