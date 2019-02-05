package it.sevenbits.lexers.lexems;

import it.sevenbits.lexers.tokens.IToken;
import it.sevenbits.lexers.tokens.Token;

import java.util.List;

public class InlineCommentCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    public InlineCommentCombiner(List<IToken> tokens, List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    public void execute() {
        tokens.set(tokens.size()-1,
                new Token(tokens.get(tokens.size()-1).getText() + buff.get(0).getText(),
                        "INLINE_COMMENT"));
        buff.remove(0);
    }
}