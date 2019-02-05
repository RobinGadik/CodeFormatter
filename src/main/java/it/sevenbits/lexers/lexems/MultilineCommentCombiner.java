package it.sevenbits.lexers.lexems;

import it.sevenbits.lexers.tokens.IToken;
import it.sevenbits.lexers.tokens.Token;

import java.util.List;

public class MultilineCommentCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    public MultilineCommentCombiner(List<IToken> tokens, List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    public void execute() {
        if ("END_LINE" == buff.get(0).getType()) {
            tokens.add(new Token("","MULTILINE_COMMENT"));
            buff.remove(0);
        } else {
            if ("SPACE" != buff.get(0).getType() || 0 < tokens.get(tokens.size() - 1).getText().length()) {
                tokens.set(tokens.size() - 1,
                        new Token(tokens.get(tokens.size() - 1).getText() + buff.get(0).getText(),
                                "MULTILINE_COMMENT"));
            }
            buff.remove(0);
        }
    }
}