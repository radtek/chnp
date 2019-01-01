package chn.hib.enums;

public enum SQLOperator {

    EQUAL("="),
    GREAT_THAN(">"),
    GREAT_EQUAL(">="),
    LESS_THAN("<"),
    LESS_EQUAL("<="),
    LIKE("like");

    private String operator;
    SQLOperator(String op) {
        this.operator = op;
    }

    @Override
    public String toString() {
        return this.operator;
    }
}