package it.sevenbits.lexers.tokens;

public interface IToken {

    /**
     * @return text token
     */
    String getText();

    /**
     * @return token type
     */
    String getType();
}
