package it.sevenbits.lexers.lexems;

import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InlineCommentCombinerTest {
    @Test
    public void executeStart() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        InlineCommentCombiner c = new InlineCommentCombiner(tokens, buff);
        tokens.add(new Token("/","SLASH"));
        buff.add(new Token("/","SLASH"));
        c.execute();
        assertEquals( new Token("//", "INLINE_COMMENT"), tokens.get(tokens.size()-1));
    }
    @Test
    public void executeRegular() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        InlineCommentCombiner c = new InlineCommentCombiner(tokens, buff);
        tokens.add(new Token("/","SLASH"));
        buff.add(new Token("/","SLASH"));
        buff.add(new Token("g","CUSTOM"));
        buff.add(new Token("\"","QUOTE"));
        c.execute();
        c.execute();
        c.execute();
        assertEquals( new Token("//g\"", "INLINE_COMMENT"), tokens.get(tokens.size()-1));
    }

}