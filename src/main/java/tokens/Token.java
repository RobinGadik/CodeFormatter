package tokens;

/**
 * Lexama token
 */
public class Token implements IToken {
    private String text;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Token token = (Token) o;

        if (getText() != null ? !getText().equals(token.getText()) : token.getText() != null) {
            return false;
        }
        return getType() != null ? getType().equals(token.getType()) : token.getType() == null;
    }

    @Override
    public int hashCode() {
        int result = getText() != null ? getText().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    /**
     * @param text token text
     * @param type token type
     */
    public Token(final String text, final String type) {
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
