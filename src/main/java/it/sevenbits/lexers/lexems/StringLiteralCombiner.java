package it.sevenbits.lexers.lexems;

import tokens.IToken;
import tokens.Token;

import java.util.List;

public class StringLiteralCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    public StringLiteralCombiner(List<IToken> tokens, List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    public void execute() {
        if(1 > tokens.size()) {
            tokens.add(new Token("","STRING_LITERAL"));
        }
        if (tokens.get(tokens.size()-1).getType().equals("STRING_LITERAL")) {
            tokens.set(tokens.size()-1,
                    new Token(tokens.get(tokens.size()-1).getText() + buff.get(0).getText(), "STRING_LITERAL"));
            buff.remove(0);
        } else {
            tokens.add(new Token(buff.get(0).getText(), "STRING_LITERAL"));
            buff.remove(0);
        }
    }
}