package com.luxoft.simplify.parsers3;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// JC: Polynomial Parser
public class PolynomialParser1 {

    public static void main(String[] args) {
        String input = "-2(x+1)(2x-2)";
        String pattern = "-?\\d+|[a-z]+(?:\\^\\d+)?|\\((?:[^()]|\\([^()]*\\))*\\)";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);

        while (m.find()) {
            System.out.println(m.group());
        }
    }

}
