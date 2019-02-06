package it.sevenbits.lexers.lexems;

import tokens.IToken;

import java.util.List;

public class EndLineCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    public EndLineCombiner(List<IToken> tokens, List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    public void execute() {
        tokens.add(buff.get(0));
        buff.remove(0);
    }
}