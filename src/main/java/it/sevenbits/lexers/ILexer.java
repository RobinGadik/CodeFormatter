package it.sevenbits.lexers;

import tokens.IToken;

/**
 * lexems token splitter
 */
public interface ILexer {
    /**
     * @return lexeme token
     * @throws LexerException if some fail in divide to tokens
     */
    IToken nextToken() throws LexerException;

    /**
     * @return true if more tokkens avialiable
     */
    boolean hasNextToken();
}
