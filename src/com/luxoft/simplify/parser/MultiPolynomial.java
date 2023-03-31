package com.luxoft.simplify.parser;

import java.util.ArrayList;
import java.util.List;

public class MultiPolynomial {
    private List<PolynomialWrapper> factors;

    public MultiPolynomial(List<PolynomialWrapper> factors) {
        this.factors = factors;
    }

    public List<PolynomialWrapper> getFactors() {
        return factors;
    }

    public void setFactors(List<PolynomialWrapper> factors) {
        this.factors = factors;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < factors.size(); i++) {
            builder.append(factors.get(i).toString());
        }
        return builder.toString();
    }

    // convert "-2(x+1)(2x-2)" to ["2","x+1","2x-2"]
    private static String[] split(String input) {
        // Remove whitespaces
        String trimmedInput = input.replaceAll("\\s+", "");

        List<String> expressions = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int openParentheses = 0;

        for (int i = 0; i < trimmedInput.length(); i++) {
            char c = trimmedInput.charAt(i);
            if (c == '(') {
                openParentheses++;
            } else if (c == ')') {
                openParentheses--;
            }
            sb.append(c);

            // If the current character is a closing parenthesis and the number of open parentheses is zero
            // or the next character is an opening parenthesis, add the current expression to the list and reset the StringBuilder
            if ((c == ')' && openParentheses == 0) || (i < trimmedInput.length() - 1 && trimmedInput.charAt(i + 1) == '(')) {
                String expression = sb.toString();
                // Remove outer parentheses if they exist
                if (expression.startsWith("(") && expression.endsWith(")")) {
                    expression = expression.substring(1, expression.length() - 1);
                }
                expressions.add(expression);
                sb.setLength(0);
            }
        }

        if (sb.length() > 0) {
            expressions.add(sb.toString());
        }

        return expressions.toArray(new String[0]);
    }

    public static MultiPolynomial fromString(String expression) {
        List<PolynomialWrapper> factors = new ArrayList<>();
        String[] factorStrings = split(expression);
        for (String factorString : factorStrings) {
            if (factorString.equals("-")) {
                factors.add(PolynomialWrapper.fromString("-1"));
            } else if (!factorString.equals("+")) {
                factors.add(PolynomialWrapper.fromString(factorString.trim()));
            }
        }
        return new MultiPolynomial(factors);
    }

    public PolynomialWrapper simplify() {
        for (PolynomialWrapper factor : factors) {
            factor.simplify();
        }
        if (factors.size() > 1) {
            PolynomialWrapper product = factors.get(0);
            for (int i = 1; i < factors.size(); i++) {
                product = product.multiply(factors.get(i));
            }
            product.simplify();
            return product;
        } else {
            return factors.get(0);
        }
    }

    public void negate() {
        factors.get(0).negate();
    }
}
