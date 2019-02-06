package it.sevenbits.lexers.lexems;

import tokens.IToken;
import tokens.Token;

import java.util.List;

/**
 *
 */
public class InlineCommentCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    /**
     * @param tokens output lexem-tokens list
     * @param buff buff of char tokens
     */
    public InlineCommentCombiner(final List<IToken> tokens, final List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    @Override
    public void execute() {
        tokens.set(tokens.size() - 1,
                new Token(tokens.get(tokens.size() - 1).getText() + buff.get(0).getText(),
                        "INLINE_COMMENT"));
        buff.remove(0);
    }
}