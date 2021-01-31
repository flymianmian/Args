package com.trs.laiyi.args;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsParser {

    public static List<String> parseArgsAsText(String argsAsText) throws ArgsException {
        if (argsAsText.isEmpty()) {
            throw new ArgsException("can not parse empty text!");
        }
//        return Arrays.asList("-l", "-p:8080", "-d:/usr/logs");
        List<String> results = new ArrayList<>();
        Matcher matcher = Pattern.compile("((-[a-z]) ([^-\\s]*))").matcher(argsAsText);
        while (matcher.find()) {
            if (matcher.group(3).isEmpty()) {
                results.add(matcher.group(2));
            } else {
                results.add(matcher.group(2) + ":" + matcher.group(3));
            }
        }
        return results;
    }
}
