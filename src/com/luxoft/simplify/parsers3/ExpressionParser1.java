package com.luxoft.simplify.parsers3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JC: Expression Parser
 *
 * PROMPT:
 *
 create a Java convertor that splits expression with the sum
 of several Polynomials to separate Polynomials,
 for example "(x+1)(2x-2)-2(x+1)"
 should become ["(x+1)(2x-2)","-2(x+1)"]
 */
public class ExpressionParser1 {

    public static void main(String[] args) {
        String expression = "(x+1)(2x-2)-2(x+1)+(3x^2+x)-x(5-x)(x^2+1)-25";
        String[] polynomials = splitPolynomial(expression);
        System.out.println(Arrays.toString(polynomials));
    }

    public static String[] splitPolynomial(String expression) {
        List<String> polynomialList = new ArrayList<>();
        int depth = 0;
        int start = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                depth++;
            } else if (expression.charAt(i) == ')') {
                depth--;
            } else if (depth == 0 && (expression.charAt(i) == '+' || expression.charAt(i) == '-')) {
                polynomialList.add(expression.substring(start, i));
                start = i;
            }
        }
        polynomialList.add(expression.substring(start));
        String[] polynomialArray = new String[polynomialList.size()];
        polynomialArray = polynomialList.toArray(polynomialArray);
        return polynomialArray;
    }
}
