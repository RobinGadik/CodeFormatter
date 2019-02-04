package it.sevenbits.lexers.lexems;

import it.sevenbits.lexers.tokens.IToken;
import it.sevenbits.lexers.tokens.Token;

import java.util.List;

public class OpenBlockCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    List<IToken> buff;

    public OpenBlockCombiner(List<IToken> tokens, List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    public void execute() {
        System.out.println("FUUU");
        System.out.println(buff.toString());
        tokens.add(buff.get(0));
        buff.remove(0);
    }
}
