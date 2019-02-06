package it.sevenbits.lexers;

import it.sevenbits.IO.IReader;

import java.io.IOException;

/**
 *
 */
public interface ILexerFactory {
    /**
     * @param in IReader for parse to lexemes
     * @return ILexer, ready to use
     * @throws IOException from IReader
     */
    ILexer getLexer(IReader in) throws IOException;
}
