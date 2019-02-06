package it.sevenbits.lexers.lexems;

import tokens.IToken;

import java.util.List;

/**
 *
 */
public class CommentStartCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    /**
     * @param tokens output lexem-tokens list
     * @param buff buff of char tokens
     */
    public CommentStartCombiner(final List<IToken> tokens, final List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    @Override
    public void execute() {
        tokens.add(buff.get(0));
        buff.remove(0);
    }
}