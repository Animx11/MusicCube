package musiccube.advancedsearchv2;

import java.util.HashMap;

class NameInQueryBuilder {
    private NameInQueryBuilder() {}
    static void build(NameInSearchDecorator decorator, StringBuilder builder) {
        String[] namesArr = decorator.paramValues.split(",");
        int length = namesArr.length;
        for (int i = 0; i < length; i++) {
            decorator.queryParams.put((decorator.nameRoot+i),namesArr[i]);
            builder
                    .append("LOWER(")
                    .append(decorator.propName)
                    .append(") ");
            if (decorator.negated) {
                builder.append("NOT ");
            }
            builder
                    .append("LIKE LOWER(CONCAT('%',:")
                    .append(decorator.nameRoot)
                    .append(i)
                    .append(",'%')) ");
            if (i < length - 1) {
                builder.append("OR ");
            }
        }
    }
    static void build(StringBuilder query, HashMap queryParams, String names, String nameRoot, String propName) {
        String[] namesArr = names.split(",");
        int length = namesArr.length;
        for (int i = 0; i < length; i++) {
            queryParams.put((nameRoot + i), namesArr[i]);
            query
                    .append("LOWER(")
                    .append(propName)
                    .append(") ")
                    .append("LIKE LOWER(CONCAT('%',:")
                    .append(nameRoot)
                    .append(i)
                    .append(",'%')) ");
            if (i < length - 1) {
                query.append("OR ");
            }
        }
    }
}