package com.luxoft.simplify.parser;

public class MonomialWrapper implements Comparable<MonomialWrapper> {
    private int coefficient;
    private int exponent;

    public MonomialWrapper(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public int evaluate(int x) {
        return coefficient * (int)Math.pow(x, exponent);
    }

    @Override
    public String toString() {
        if (exponent == 0) {
            return "" + coefficient;
        } else if (exponent == 1) {
            if (coefficient == 1) return "x";
            if (coefficient == -1) return "-x";
            return coefficient + "x";
        } else {
            if (coefficient == 1) return "x^"+exponent;
            if (coefficient == -1) return "-x^"+exponent;
            return coefficient + "x^" + exponent;
        }
    }

    public static MonomialWrapper fromString(String monomialString) {
        int exponent = 0;
        int coefficient = 0;
        if (monomialString.contains("x")) {
            String[] parts = monomialString.split("\\^");
            if (parts.length == 2) {
                exponent = Integer.parseInt(parts[1]);
            } else {
                exponent = 1;
            }
            String coefficientString = parts[0].replace("x", "");
            if (coefficientString.equals("")) {
                coefficient = 1;
            } else if (coefficientString.equals("+")) {
                coefficient = 1;
            } else if (coefficientString.equals("-")) {
                coefficient = -1;
            } else {
                coefficient = Integer.parseInt(coefficientString);
            }
        } else {
            coefficient = Integer.parseInt(monomialString);
        }
        return new MonomialWrapper(coefficient, exponent);
    }

    @Override
    public int compareTo(MonomialWrapper o) {
        return o.getExponent()-getExponent();
    }

    public void negate() {
        this.coefficient = -this.coefficient;
    }
}
