package it.sevenbits.lexers;

import it.sevenbits.IO.IReader;

import java.io.IOException;

public interface ILexerFactory {
    ILexer getLexer(IReader in) throws IOException;
}
