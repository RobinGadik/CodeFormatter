package it.sevenbits.lexers.lexems;

import tokens.IToken;
import tokens.Token;

import java.util.List;

/**
 *
 */
public class StringLiteralCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    /**
     * @param tokens output lexem-tokens list
     * @param buff buff of char tokens
     */
    public StringLiteralCombiner(final List<IToken> tokens, final List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    @Override
    public void execute() {
        if (1 > tokens.size()) {
            tokens.add(new Token("", "STRING_LITERAL"));
        }
        if (tokens.get(tokens.size() - 1).getType().equals("STRING_LITERAL")) {
            tokens.set(tokens.size() - 1,
                    new Token(tokens.get(tokens.size() - 1).getText() + buff.get(0).getText(), "STRING_LITERAL"));
            buff.remove(0);
        } else {
            tokens.add(new Token(buff.get(0).getText(), "STRING_LITERAL"));
            buff.remove(0);
        }
    }
}