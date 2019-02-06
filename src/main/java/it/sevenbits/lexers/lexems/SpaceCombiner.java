package it.sevenbits.lexers.lexems;

import tokens.IToken;

import java.util.List;

/**
 *
 */
public class SpaceCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    /**
     * @param tokens output lexem-tokens list
     * @param buff buff of char tokens
     */
    public SpaceCombiner(final List<IToken> tokens, final List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    @Override
    public void execute() {
        if (0 < tokens.size() && tokens.get(tokens.size() - 1).getType().equals("USER_DEFINE")) {
            tokens.add(buff.get(0));
        }
        buff.remove(0);
    }
}
