package musiccube.modelgenerator;

import org.json.JSONArray;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Constants {
    private Constants() {}
    static final String SAVED = " successfully saved.";
    static final String BGN_AREA = "begin_area";
    static final String LF_SPAN = "life-span";
    static final String FMT = "?fmt=json";
    static final String EXISTS = " already exists.";
    static final int ITERATIONS = 10;
    static final int OFFSET = 0;

    static Stream<Object> arrayToStream(JSONArray array) {
        return StreamSupport.stream(array.spliterator(), false);
    }
}
