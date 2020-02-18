package musiccube.advancedsearchv2;

class NameInSearchDecorator extends AbstractAdvancedSearchWithParams {
    String nameRoot;
    String propName;
    NameInSearchDecorator(AbstractAdvancedSearch decorated, boolean negated, String paramVals, String nameRoot, String propName) {
        super(decorated, negated, paramVals);
        this.nameRoot=nameRoot;
        this.propName=propName;
    }

    @Override
    StringBuilder getQuery() {
        StringBuilder builder = decorated.getQuery().append("(");
        NameInQueryBuilder.build(this,builder);
        builder.append(") ");
        return builder;
    }
}