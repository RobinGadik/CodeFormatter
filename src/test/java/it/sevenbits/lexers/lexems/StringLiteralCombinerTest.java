package it.sevenbits.lexers.lexems;

import org.junit.Test;
import tokens.IToken;
import tokens.Token;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StringLiteralCombinerTest {
    @Test
    public void executeStart() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        StringLiteralCombiner c = new StringLiteralCombiner(tokens, buff);
        buff.add(new Token("\"","QUOTE"));
        c.execute();
        assertEquals( new Token("\"", "STRING_LITERAL"), tokens.get(0));
    }

    @Test
    public void executeDefault() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        StringLiteralCombiner c = new StringLiteralCombiner(tokens, buff);
        tokens.add(new Token("\"" , "STRING_LITERAL"));
        buff.add(new Token("1","USER_DEFINE"));
        c.execute();
        assertEquals( new Token("\"1", "STRING_LITERAL"), tokens.get(tokens.size() - 1));
    }

    @Test
    public void executeEnd() throws Exception {
        List<IToken> tokens = new ArrayList<>();
        List<IToken> buff = new ArrayList<>();
        StringLiteralCombiner c = new StringLiteralCombiner(tokens, buff);
        tokens.add(new Token("\"123" , "STRING_LITERAL"));
        buff.add(new Token("\"","QUOTE"));
        c.execute();
        assertEquals( new Token("\"123\"", "STRING_LITERAL"), tokens.get(tokens.size() - 1));
    }


}