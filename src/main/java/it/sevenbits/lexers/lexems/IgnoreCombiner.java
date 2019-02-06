package it.sevenbits.lexers.lexems;

import tokens.IToken;

import java.util.List;

/**
 *
 */
public class IgnoreCombiner  implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    /**
     * @param tokens output lexem-tokens list
     * @param buff buff of char tokens
     */
    public IgnoreCombiner(final List<IToken> tokens, final List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    @Override
    public void execute() {
        buff.remove(0);
    }
}