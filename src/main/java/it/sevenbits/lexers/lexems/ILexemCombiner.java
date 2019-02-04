package it.sevenbits.lexers.lexems;

import it.sevenbits.lexers.tokens.IToken;

import java.util.List;

/**
 * Detect combination of lexem and
 */
public interface ILexemCombiner {

    /**
     * Combine buf to lexem
     */
    void execute();
}
