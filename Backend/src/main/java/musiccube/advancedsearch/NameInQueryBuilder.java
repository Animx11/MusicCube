package musiccube.advancedsearch;

class NameInQueryBuilder {
    private NameInQueryBuilder() {}
    static String build(String attributeName, String paramValues, boolean negated){
        String[] titlesArr = paramValues.split(",");
        StringBuilder query = new StringBuilder("(LOWER(").append(attributeName).append(") ");
        if (negated) {
            query.append("NOT ");
        }
        query.append("IN( ");
        int len = titlesArr.length;
        for (int i = 0; i < len; i++) {
            query.append("LOWER(").append(titlesArr[i]).append(")");
            if (i < len-1) {
                query.append(",");
            }
            query.append(" ");
        }
        query.append(") ");

        return query.toString();
    }
}
