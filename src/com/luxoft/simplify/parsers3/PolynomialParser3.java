package com.luxoft.simplify.parsers3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialParser3 {

    public static void main(String[] args) {

        String input = "-2(x+1)(2x-2)";
        String[] parsedExpressions = parseExpression(input);

        System.out.println(Arrays.toString(parsedExpressions));
    }

    public static String[] parseExpression(String input) {
        String pattern = "-?\\d+|[a-z]+(?:\\^\\d+)?|\\((?:[^()]|\\([^()]*\\))*\\)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        ArrayList<String> terms = new ArrayList<String>();
        while (m.find()) {
            String term = m.group();
            if (term.startsWith("(") && term.endsWith(")")) {
                term = term.substring(1, term.length() - 1);
            }
            terms.add(term);
        }
        return terms.toArray(new String[0]);
    }

}
