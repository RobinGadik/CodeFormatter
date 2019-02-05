package it.sevenbits.lexers.lexems;

import it.sevenbits.lexers.tokens.IToken;
import it.sevenbits.lexers.tokens.Token;

import java.util.List;

public class CommentStartCombiner implements ILexemCombiner {

    private List<IToken> tokens;
    private List<IToken> buff;

    public CommentStartCombiner(List<IToken> tokens, List<IToken> buff) {
        this.tokens = tokens;
        this.buff = buff;
    }

    public void execute() {
        System.out.println(tokens);
        System.out.println(buff);
        tokens.add(buff.get(0));
        buff.remove(0);

    }
}