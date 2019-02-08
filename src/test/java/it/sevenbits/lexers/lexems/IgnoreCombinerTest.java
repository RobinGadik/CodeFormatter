package it.sevenbits.lexers.lexems;

import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IgnoreCombinerTest {
    @Test
    public void execute() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        IgnoreCombiner c = new IgnoreCombiner(tokens, buff);
        buff.add(new Token("/","SLASH"));
        c.execute();
        assertTrue(tokens.isEmpty());
        assertTrue(buff.isEmpty());
    }

}