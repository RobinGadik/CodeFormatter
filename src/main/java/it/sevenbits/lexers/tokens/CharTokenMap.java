package it.sevenbits.lexers.tokens;

import java.util.HashMap;
import java.util.Map;

public class CharTokenMap {
    private final Map<String, String> typeMap;
    private final String defaultType = "CUSTOM";

    public CharTokenMap() {
        typeMap = new HashMap<>();

        //typeMap.put(";","END_CMD");
        //typeMap.put("\n","END_LINE");
        typeMap.put("{","OPEN_BLOCK");
        typeMap.put("}","END_BLOCK");
        typeMap.put("\"","QUOTE");
        //typeMap.put("/","SLASH");
        //ypeMap.put("*","STAR");
        //typeMap.put("\0","FILE_END");
        //typeMap.put(".","POINT");
        typeMap.put(" ","SPACE");
        //typeMap.put("+","SPECIAL");
        //typeMap.put("-","SPECIAL");
        //typeMap.put("=","SPECIAL");
        //typeMap.put("","");
        //typeMap.put("","");

    }

    public IToken getToken(char c) {
        return new Token(String.valueOf(c), typeMap.getOrDefault(String.valueOf(c), defaultType));
    }

}
