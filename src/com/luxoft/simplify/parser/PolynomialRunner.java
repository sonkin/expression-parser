package com.luxoft.simplify.parser;

public class PolynomialRunner {

    public static void main(String[] args) {
        MultiPolynomialSum multi = MultiPolynomialSum
                .fromString("(x+1)(2x-2)-2(x+1)+(3x^2+x)-x(5-x)(x^2+1)-25");
        System.out.println(multi.simplify());

        MultiPolynomialSum multi3 = MultiPolynomialSum
                .fromString("4(x+2)+(3x^2+x) - x(5-x)(x^2+1)-25");
        System.out.println(multi3.simplify());

        String expression = "4(x+2)+3x^2+x = x(5-x)(x^2+1)+25";
        System.out.println(simplifyEquation(expression));
        String expression2 = "4(x+2)+3x^2+x-x(5-x)(x^2+1)+25=x^5-x+10-13+x(x^1+2)(x^2-1)(25-13)-3x^4-(x-2)";
        System.out.println(simplifyEquation(expression2));
    }

    private static String simplifyEquation(String expression) {
        String[] expressions = expression.split("=");
        MultiPolynomialSum exLeft = MultiPolynomialSum.fromString(expressions[0]);
        MultiPolynomialSum exRight = MultiPolynomialSum.fromString(expressions[1]);
        return exLeft.add(exRight.negate()).simplify().toString()+" = 0";
    }
}
