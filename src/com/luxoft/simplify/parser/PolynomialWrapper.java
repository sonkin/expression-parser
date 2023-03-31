package com.luxoft.simplify.parser;

import java.util.*;

public class PolynomialWrapper {
    private List<MonomialWrapper> terms;

    public PolynomialWrapper() {
        this.terms = new ArrayList<>();
    }

    public PolynomialWrapper(List<MonomialWrapper> terms) {
        this.terms = terms;
    }

    public List<MonomialWrapper> getTerms() {
        return terms;
    }

    public void setTerms(List<MonomialWrapper> terms) {
        this.terms = terms;
    }

    public int evaluate(int x) {
        int result = 0;
        for (MonomialWrapper term : terms) {
            result += term.evaluate(x);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
            if (i > 0) {
                builder.append(terms.get(i).getCoefficient() >= 0 ? "+" : "");
            }
            builder.append(terms.get(i).toString());
        }
        return builder.toString();
    }

    public PolynomialWrapper multiply(PolynomialWrapper other) {
        List<MonomialWrapper> resultTerms = new ArrayList<>();
        for (MonomialWrapper term1 : terms) {
            for (MonomialWrapper term2 : other.getTerms()) {
                int newCoefficient = term1.getCoefficient() * term2.getCoefficient();
                int newExponent = term1.getExponent() + term2.getExponent();
                resultTerms.add(new MonomialWrapper(newCoefficient, newExponent));
            }
        }
        return new PolynomialWrapper(resultTerms);
    }

    public void simplify() {
        Map<Integer, Integer> termMap = new HashMap<>();
        for (MonomialWrapper term : terms) {
            if (termMap.containsKey(term.getExponent())) {
                termMap.put(term.getExponent(), termMap.get(term.getExponent()) + term.getCoefficient());
            } else {
                termMap.put(term.getExponent(), term.getCoefficient());
            }
        }
        terms.clear();
        for (int exponent : termMap.keySet()) {
            int coefficient = termMap.get(exponent);
            if (coefficient != 0) {
                terms.add(new MonomialWrapper(coefficient, exponent));
            }
        }
        Collections.sort(terms);
    }

    public PolynomialWrapper add(PolynomialWrapper other) {
        List<MonomialWrapper> sumMonomials = new ArrayList<>(this.terms);
        for (MonomialWrapper monomial : other.getTerms()) {
            int index = sumMonomials.indexOf(monomial);
            if (index == -1) {
                sumMonomials.add(monomial);
            } else {
                MonomialWrapper sumMonomial = sumMonomials.get(index);
                sumMonomial.setCoefficient(sumMonomial.getCoefficient() + monomial.getCoefficient());
            }
        }
        return new PolynomialWrapper(sumMonomials);
    }

    public static PolynomialWrapper fromString(String expression) {
        String[] termStrings = expression.split("(?=[+-])");
        List<MonomialWrapper> terms = new ArrayList<>();
        for (String termString : termStrings) {
            terms.add(MonomialWrapper.fromString(termString.trim()));
        }
        return new PolynomialWrapper(terms);
    }

    public PolynomialWrapper negate() {
        for (MonomialWrapper monomialWrapper: getTerms()) {
            monomialWrapper.negate();
        }
        return this;
    }
}
