package it.sevenbits.lexers.lexems;

import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MultilineCommentCombinerTest {
    @Test
    public void executeStart() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        MultilineCommentCombiner c = new MultilineCommentCombiner(tokens, buff);
        tokens.add(new Token("/","SLASH"));
        buff.add(new Token("*","STAR"));
        c.execute();
        assertEquals( new Token("/*", "MULTILINE_COMMENT"), tokens.get(tokens.size()-1));
    }
    @Test
    public void executeRegular() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        MultilineCommentCombiner c = new MultilineCommentCombiner(tokens, buff);
        tokens.add(new Token("/","SLASH"));
        buff.add(new Token("*","STAR"));
        buff.add(new Token("\n","END_LINE"));
        buff.add(new Token("\"","QUOTE"));
        c.execute();
        c.execute();
        c.execute();
        assertEquals( new Token("\"", "MULTILINE_COMMENT"), tokens.get(tokens.size()-1));
    }

    @Test
    public void executeEnd() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        MultilineCommentCombiner c = new MultilineCommentCombiner(tokens, buff);
        tokens.add(new Token("/","SLASH"));
        buff.add(new Token("*","STAR"));
        buff.add(new Token("\n","END_LINE"));
        buff.add(new Token("\"","QUOTE"));
        buff.add(new Token("*","STAR"));
        buff .add(new Token("/","SLASH"));
        c.execute();
        c.execute();
        c.execute();
        c.execute();
        c.execute();
        assertEquals( new Token("\"*/", "MULTILINE_COMMENT"), tokens.get(tokens.size()-1));
    }


}