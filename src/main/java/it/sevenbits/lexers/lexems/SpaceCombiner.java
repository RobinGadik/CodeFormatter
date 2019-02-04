package it.sevenbits.lexers.lexems;

import it.sevenbits.lexers.tokens.IToken;

import java.util.List;

public class SpaceCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    public SpaceCombiner(List<IToken> tokens, List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    public void execute() {
        if (0 < tokens.size() && "USER_DEFINE" == tokens.get(tokens.size() - 1).getType()) {
            tokens.add(buff.get(0));
        }
        buff.remove(0);
    }
}
