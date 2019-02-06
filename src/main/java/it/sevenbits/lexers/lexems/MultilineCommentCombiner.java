package it.sevenbits.lexers.lexems;

import tokens.IToken;
import tokens.Token;

import java.util.List;

/**
 *
 */
public class MultilineCommentCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    /**
     * @param tokens output lexem-tokens list
     * @param buff buff of char tokens
     */
    public MultilineCommentCombiner(final List<IToken> tokens, final List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    @Override
    public void execute() {
        if ("END_LINE".equals(buff.get(0).getType())) {
            tokens.add(new Token("", "MULTILINE_COMMENT"));
            buff.remove(0);
        } else {
            if (!"SPACE".equals(buff.get(0).getType()) || 0 < tokens.get(tokens.size() - 1).getText().length()) {
                tokens.set(tokens.size() - 1,
                        new Token(tokens.get(tokens.size() - 1).getText() + buff.get(0).getText(),
                                "MULTILINE_COMMENT"));
            }
            buff.remove(0);
        }
    }
}