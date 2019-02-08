package it.sevenbits.lexers.lexems;

import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CloneCombinerTest {
    @Test
    public void executeBegin() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        CloneCombiner c = new CloneCombiner(tokens, buff);
        buff.add(new Token("a","CUSTOM"));
        c.execute();
        assertEquals( new Token("a", "USER_DEFINE"), tokens.get(0));
    }
    @Test
    public void executeNewToken() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        CloneCombiner c = new CloneCombiner(tokens, buff);
        buff.add(new Token("a","CUSTOM"));
        tokens.add(new Token("123456","STRING_LITERAL"));
        c.execute();
        assertEquals( new Token("a", "USER_DEFINE"), tokens.get(tokens.size()-1));
    }

    @Test
    public void executeAdding() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        CloneCombiner c = new CloneCombiner(tokens, buff);
        buff.add(new Token("b","CUSTOM"));
        tokens.add(new Token("a","USER_DEFINE"));
        c.execute();
        assertEquals( new Token("ab", "USER_DEFINE"), tokens.get(0));
    }

}