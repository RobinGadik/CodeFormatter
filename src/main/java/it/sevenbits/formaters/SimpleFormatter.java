package it.sevenbits.formaters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * old rule's based formatter
 */
public class SimpleFormatter {

    private List<Function<List<String> , List<String>> > rules;
    //private Map<String, String> literalSwap;

    /**
     * @param in String to format
     * @return formated in
     */
    public String format(final String in) {
        List<String> tokens = new ArrayList<>();
        tokens.add(in);
        for (Function< List<String> , List<String> > r:rules) {
            tokens = r.apply(tokens);
        }

        String outString = "";

        for (String str:tokens) {
            outString = outString.concat(str);
        }

        return outString;
    }

    /**
     * base rule's constructor
     */
    public SimpleFormatter() {
        rules = new ArrayList<>();
        rules.add(
                (List<String> s) -> {
                    ArrayList<String> tokens = new ArrayList<>();
                    for (String bigString:s) {
                        Collections.addAll(tokens, bigString.split(" "));
                    }
                    return tokens;
                }
        );

        rules.add(
                (List<String> tokens) -> {
                    ArrayList<String> tokensExpanded = new ArrayList<>();
                    // userDefineString for '' and "" and so one
                    for (String token:tokens) {
                        String temp = "";
                        for (char c:token.toCharArray()) {
                            if (c == ';' || c == '{' || c == '}') {
                                if (!temp.isEmpty()) {
                                    tokensExpanded.add(temp);
                                    temp = "";
                                }
                                tokensExpanded.add(String.valueOf(c));
                            } else {
                                temp = temp.concat(String.valueOf(c));
                            }
                        }
                        if (!temp.isEmpty()) {
                            tokensExpanded.add(temp);
                        }
                    }
                    return tokensExpanded;
                }
        );

        rules.add(
                (List<String> tokens) -> {
                    int depth = 0;
                    ArrayList<String> tokensWithSpace = new ArrayList<>();
                    boolean someString = false;
                    for (String token : tokens) {
                        //I don't like that, but....
                        if (token.equals("{")) {
                            depth++;
                            tokensWithSpace.add(" ");
                            tokensWithSpace.add(token);
                            tokensWithSpace.add("\n");
                            someString = false;

                        } else if (token.equals("}")) {
                            depth--;
                            tokensWithSpace.add(token);
                            tokensWithSpace.add("\n");
                            for (int i = 0; i < depth + depth + depth + depth ; i++) {
                                tokensWithSpace.add(" ");
                            }
                            someString = false;

                        } else if (token.equals(";")) {
                            tokensWithSpace.add(token);
                            tokensWithSpace.add("\n");
                            someString = false;

                        } else {
                            if (!someString) {
                                for (int i = 0 ; i < depth + depth + depth + depth ; i++) {
                                    tokensWithSpace.add(" ");
                                }
                                someString = true;
                            } else {
                                tokensWithSpace.add(" ");
                            }
                            tokensWithSpace.add(token);
                        }
                    }
                    return tokensWithSpace;
                }
        );
    }


}
