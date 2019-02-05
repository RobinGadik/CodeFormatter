package it.sevenbits.lexers;

import it.sevenbits.IO.IReader;
import it.sevenbits.lexers.sm.LexerStateMachine;

import java.io.IOException;

public class LexerStateMachineFactory implements ILexerFactory {
    @Override
    public ILexer getLexer(final IReader in) throws IOException {
        return new LexerStateMachine(in);
    }
}
