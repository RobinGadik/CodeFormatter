package it.sevenbits.lexers.lexems;

import it.sevenbits.lexers.tokens.IToken;

import java.util.List;

public class IgnoreCombiner  implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    public IgnoreCombiner(List<IToken> tokens, List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    public void execute() {
        buff.remove(0);
    }
}