package it.sevenbits.lexers.lexems;

import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpaceCombinerTest {
    @Test
    public void executeStart() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        SpaceCombiner c = new SpaceCombiner(tokens, buff);
        buff.add(new Token(" ","SPACE"));
        c.execute();
        assertTrue(tokens.isEmpty());
    }
    @Test
    public void executeDefault() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        SpaceCombiner c = new SpaceCombiner(tokens, buff);
        tokens.add(new Token("123" , "USER_DEFINE"));
        buff.add(new Token(" ","SPACE"));
        c.execute();
        assertEquals( new Token(" ", "SPACE"), tokens.get(tokens.size() - 1));
    }

    @Test
    public void executeAfterNorUserDefine() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        SpaceCombiner c = new SpaceCombiner(tokens, buff);
        tokens.add(new Token("/" , "SLASH"));
        buff.add(new Token(" ","SPACE"));
        c.execute();
        assertEquals( new Token("/", "SLASH"), tokens.get(0));
    }

}