package it.sevenbits.lexers.lexems;

import tokens.IToken;
import tokens.Token;

import java.util.List;

public class CloneCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    public CloneCombiner(List<IToken> tokens, List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    public void execute() {
        if(1 > tokens.size()) {
            tokens.add(new Token("","USER_DEFINE"));
        }
        if (tokens.get(tokens.size()-1).getType().equals("USER_DEFINE")) {
            tokens.set(tokens.size()-1,
                    new Token(tokens.get(tokens.size()-1).getText() + buff.get(0).getText(), "USER_DEFINE"));
            buff.remove(0);
        } else {
            tokens.add(new Token(buff.get(0).getText(), "USER_DEFINE"));
            buff.remove(0);
        }
    }
}
