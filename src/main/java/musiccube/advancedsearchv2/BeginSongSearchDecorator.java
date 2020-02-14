package musiccube.advancedsearchv2;

import java.util.HashMap;

class BeginSongSearchDecorator extends AbstractAdvancedSearch {
    BeginSongSearchDecorator(HashMap queryParams) {
        super(null);
        this.queryParams = queryParams;
    }

    @Override
    StringBuilder getQuery() {
        return new StringBuilder("SELECT s FROM Song s ");
    }
}
