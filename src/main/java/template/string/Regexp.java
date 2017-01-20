package template.string;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dy on 17-1-12.
 *
 * Caution!! To match '\' in text, you need '\\\\' in the regexp.
 */
public class Regexp extends PatternSearch {
    private Pattern javaPattern;
    private Matcher javaMatcher;

    public Regexp(String pattern) {
        pattern = adjust(pattern);
        javaPattern = Pattern.compile(pattern);
    }

    public Regexp(String[] patterns) {
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < patterns.length; ++i) {
            patterns[i] = adjust(patterns[i]);
            if (i > 0) pattern.append("|");
            pattern.append("(").append(patterns[i]).append(")");
        }
    }

    public boolean subMatches(String text) {
        javaMatcher = javaPattern.matcher(text);
        return javaMatcher.lookingAt();
    }

    public boolean matches(String text) {
        javaMatcher = javaPattern.matcher(text);
        return javaMatcher.matches();
    }

    public List<Occurrence> search(String text) {
        List<Occurrence> res = new ArrayList<>();
        javaMatcher = javaPattern.matcher(text);
        while (javaMatcher.find()) {
            res.add(new Occurrence(javaMatcher.start(), javaMatcher.end()));
        }
        return res;
    }

    public Iterator<Occurrence> searchItr(String text) {
        javaMatcher = javaPattern.matcher(text);
        return new Iterator<Occurrence>() {
            @Override
            public boolean hasNext() {
                return javaMatcher.find();
            }

            @Override
            public Occurrence next() {
                return new Occurrence(javaMatcher.start(), javaMatcher.end());
            }
        };
    }

    public String adjust(String pattern) {
        return pattern;
    }


    public static void main(String[] args) {
        Regexp regexp = new Regexp("\\\\d+");
        System.out.println(regexp.matches("\\ddd"));
    }
}


