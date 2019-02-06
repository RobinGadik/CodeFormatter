package it.sevenbits.formaters;

import it.sevenbits.IO.IReader;
import it.sevenbits.IO.IWritter;
import it.sevenbits.lexers.LexerException;

import java.io.IOException;

public interface IFormatter {
    void format(IReader in, IWritter out) throws IOException, LexerException;
}
