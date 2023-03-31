package com.luxoft.simplify.parser;

import java.util.ArrayList;
import java.util.List;

public class MultiPolynomialSum {
    private List<MultiPolynomial> terms;

    public MultiPolynomialSum(List<MultiPolynomial> terms) {
        this.terms = terms;
    }

    public List<MultiPolynomial> getTerms() {
        return terms;
    }

    public void setTerms(List<MultiPolynomial> terms) {
        this.terms = terms;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
            if (i > 0) {
                builder.append("+");
            }
            builder.append(terms.get(i).toString());
        }
        return builder.toString();
    }

    // splits "(x+1)(2x-2)-2(x+1)" to ["(x+1)(2x-2)","-2(x+1)"]
    private static String[] split(String expression) {
        // Remove whitespaces
        String trimmedInput = expression.replaceAll("\\s+", "");

        List<String> expressions = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int openParentheses = 0;

        for (int i = 0; i < trimmedInput.length(); i++) {
            char c = trimmedInput.charAt(i);

            if (c == '(') {
                openParentheses++;
            } else if (c == ')') {
                openParentheses--;
            } else if ((c == '+' || c == '-') && openParentheses == 0) {
                expressions.add(sb.toString());
                sb.setLength(0);
            }

            sb.append(c);
        }
        expressions.add(sb.toString());

        return expressions.toArray(new String[0]);
    }

    public static MultiPolynomialSum fromString(String expression) {
        List<MultiPolynomial> terms = new ArrayList<>();
        String[] termStrings = split(expression);
        for (String termString : termStrings) {
            terms.add(MultiPolynomial.fromString(termString));
        }
        return new MultiPolynomialSum(terms);
    }

    public PolynomialWrapper simplify() {
        PolynomialWrapper result = new PolynomialWrapper();
        for (MultiPolynomial term : terms) {
            result = result.add(term.simplify());
        }
        result.simplify();
        return result;
    }

    public MultiPolynomialSum negate() {
        for (MultiPolynomial multiPolynomial: getTerms()) {
            multiPolynomial.negate();
        }
        return this;
    }

    public MultiPolynomialSum add(MultiPolynomialSum multiPolynomialSum) {
        terms.addAll(multiPolynomialSum.terms);
        return this;
    }

}
