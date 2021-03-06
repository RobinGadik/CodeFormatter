package it.sevenbits;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Formatter {

    List<Function<List<String> , List<String>> > rules;

    Map<String,String> literalSwap;

    public String format(String in){

        List<String> tokens = new ArrayList<>();
        tokens.add(in);
        for(Function< List<String> , List<String> > r:rules) {
            tokens = r.apply(tokens);
        }

        String outString = new String();

        for(String str:tokens){
            outString = outString.concat(str);
        }

        return outString;
    }

    public Formatter() {
        rules = new ArrayList<>();
        rules.add(
                (List<String> s)->{
                    ArrayList<String> tokens = new ArrayList<>();

                    for(String bigString:s){
                        for(String str:bigString.split(" ")){
                            tokens.add(str);
                        }

                    }

                    return tokens;
                }
        );

        rules.add(
                (List<String> tokens)->{


                    ArrayList<String> tokensExpanded = new ArrayList<>();

                    // userDefineString for '' and "" and so one

                    for(String token:tokens){
                        String temp = new String();

                        for(char c:token.toCharArray()){

                            if(c == ';' || c == '{' || c == '}'){
                                if(!temp.isEmpty()){
                                    tokensExpanded.add(temp);
                                    temp = new String();
                                }
                                tokensExpanded.add(String.valueOf(c));
                            }
                            else {
                                temp = temp.concat(String.valueOf(c));
                            }
                        }
                        if(!temp.isEmpty()){
                            tokensExpanded.add(temp);
                            temp = new String();
                        }
                    }


                    return tokensExpanded;
                }
        );

        rules.add(
                (List<String> tokens)->{
                    int depth=0;
                    ArrayList<String> tokensWithSpace = new ArrayList<>();
                    boolean someString = false;
                    for(String token:tokens) {
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
                            for (int i = 0; i < depth * 4; i++) {
                                tokensWithSpace.add(" ");
                            }
                            someString = false;

                        } else if (token.equals(";")) {
                            tokensWithSpace.add(token);
                            tokensWithSpace.add("\n");
                            someString = false;

                        } else {
                            if(!someString){
                                for (int i = 0; i < depth * 4; i++) {
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
