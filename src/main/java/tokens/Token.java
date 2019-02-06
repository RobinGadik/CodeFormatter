package tokens;

/**
 * Lexama token
 */
public class Token implements IToken{
    private String text;
    private String type;

    public Token(String text, String type) {
        this.text = text;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Token {" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getText() {

        return text;
    }

    public String getType() {
        return type;
    }
}
