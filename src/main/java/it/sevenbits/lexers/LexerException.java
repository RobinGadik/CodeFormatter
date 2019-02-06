package it.sevenbits.lexers;

/**
 * if some specific fail
 */
public class LexerException extends Exception {
    /**
     * @param s message
     */
    public LexerException(final String s) {
        super(s);
    }
}
