package tokens;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class CharTokenMap {
    private final Map<String, String> typeMap;
    private final String defaultType = "CUSTOM";

    /**
     * default char token parse on token types
     */
    public CharTokenMap() {
        typeMap = new HashMap<>();

        //typeMap.put(";","END_CMD");
        typeMap.put("\n", "END_LINE");
        //typeMap.put("\'","UNO_QUOTE");
        typeMap.put("{", "OPEN_BLOCK");
        typeMap.put("}", "END_BLOCK");
        typeMap.put("\"", "QUOTE");
        typeMap.put("/", "SLASH");
        typeMap.put("*", "STAR");
        //typeMap.put("\0","FILE_END");
        //typeMap.put(".","POINT");
        typeMap.put(" ", "SPACE");
        //typeMap.put("+","SPECIAL");
        //typeMap.put("-","SPECIAL");
        //typeMap.put("=","SPECIAL");
        //typeMap.put("","");
        //typeMap.put("","");

    }

    /**
     * @param c symbol
     * @return type
     */
    public IToken getToken(final char c) {
        return new Token(String.valueOf(c), typeMap.getOrDefault(String.valueOf(c), defaultType));
    }

}
