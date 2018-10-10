package it.sevenbits;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Formatter {

    List<Function<String,String>> rules;

    public Formatter() {
        rules = new ArrayList<>();
        rules.add((a)->a.concat("WORK"));
    }

    public String format(String in){
        String out = new String(in);

        for(Function<String,String> r:rules){
            out = r.apply(out);
        }
        return out;
    }

}
