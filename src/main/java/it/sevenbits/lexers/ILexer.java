package it.sevenbits.lexers;

import tokens.IToken;

/**
 * lexems token splitter
 */
public interface ILexer {
    /**
     * @return
     * @throws LexerException
     */
    IToken nextToken() throws LexerException;

    /**
     * @return true if more tokkens avialiable
     */
    boolean hasNextToken();
}
