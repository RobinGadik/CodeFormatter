package it.sevenbits.formaters;

import it.sevenbits.IO.IReader;
import it.sevenbits.IO.IWritter;
import it.sevenbits.lexers.LexerException;

import java.io.IOException;

/**
 * formatter interface
 */
public interface IFormatter {
    /**
     * @param in from this
     * @param out write here
     * @throws IOException if IO failes
     * @throws LexerException if Lexer Failed
     */
    void format(IReader in, IWritter out) throws IOException, LexerException;
}
