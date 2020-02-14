package musiccube.advancedsearchv2;

import java.util.HashMap;

class NameInQueryBuilder {
    private NameInQueryBuilder() {}
    static void build(StringBuilder query, HashMap queryParams, String names, String nameRoot, String propName) {
        String[] namesArr = names.split(",");
        int length = namesArr.length;
        for (int i = 0; i < length; i++) {
            queryParams.put((nameRoot+i),namesArr[i]);
            query
                    .append(propName)
                    .append(" LIKE :")
                    .append(nameRoot)
                    .append(i)
                    .append(" ");
            if (i < length - 1) {
                query.append("OR ");
            }
        }
    }
}