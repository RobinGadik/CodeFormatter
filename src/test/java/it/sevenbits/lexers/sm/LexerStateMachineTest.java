package it.sevenbits.lexers.sm;

import it.sevenbits.IO.IReader;
import it.sevenbits.IO.StringReader;
import it.sevenbits.lexers.ILexer;
import org.junit.Test;
import tokens.Token;

import java.io.IOException;

import static org.junit.Assert.*;

public class LexerStateMachineTest {
    IReader r;
    ILexer l;

    @Test
    public void hasNextTokenTrue() throws Exception {
        r = new StringReader("a/ \"\n// \n/*\nasd*/");
        l = new LexerStateMachine(r);
        assertTrue(l.hasNextToken());
    }
    @Test
    public void nextTokenCustom() throws Exception {
        r = new StringReader("a/ \"\n// \n/*\nasd*/");
        l = new LexerStateMachine(r);
        assertEquals(new Token("a", "USER_DEFINE"), l.nextToken());
    }
    @Test
    public void nextTokenSlash() throws Exception {
        r = new StringReader("a/ \"\n// \n/*\nasd*/");
        l = new LexerStateMachine(r);
        l.nextToken();
        assertEquals(new Token("/", "SLASH"), l.nextToken());
    }
    @Test
    public void nextTokenSpace() throws Exception {
        r = new StringReader("a \"\n// \n/*\nasd*/");
        l = new LexerStateMachine(r);
        l.nextToken();
        assertEquals(new Token(" ", "SPACE"), l.nextToken());
    }
    @Test
    public void nextTokenQuote() throws Exception {
        r = new StringReader("=\"\n// \n/*\nasd*/");
        l = new LexerStateMachine(r);
        l.nextToken();
        assertEquals(new Token("\"", "STRING_LITERAL"), l.nextToken());
    }
    @Test
    public void nextTokenENDLINE() throws Exception {
        r = new StringReader("a\"\n// \n/*\nasd*/");
        l = new LexerStateMachine(r);
        l.nextToken();
        l.nextToken();
        assertEquals(new Token("\n", "END_LINE"), l.nextToken());
    }
    @Test
    public void nextTokenINLINE() throws Exception {
        r = new StringReader("// \n/*\nasd*/");
        l = new LexerStateMachine(r);
        assertEquals(new Token("// ", "INLINE_COMMENT"), l.nextToken());
    }
    @Test
    public void nextTokenEndlineAfterInline() throws Exception {
        r = new StringReader("// \n/*\nasd*/");
        l = new LexerStateMachine(r);
        l.nextToken();
        assertEquals(new Token("\n", "END_LINE"), l.nextToken());
    }
    @Test
    public void nextTokenMULTILINE() throws Exception {
        r = new StringReader("/\n/*\nasd*/");
        l = new LexerStateMachine(r);
        l.nextToken();
        l.nextToken();
        assertEquals(new Token("/*", "MULTILINE_COMMENT"), l.nextToken());
    }
    @Test
    public void nextTokenMultileneEnd() throws Exception {
        r = new StringReader("\n/*\nasd*/");
        l = new LexerStateMachine(r);
        l.nextToken();
        l.nextToken();
        assertEquals(new Token("asd*/", "MULTILINE_COMMENT"), l.nextToken());
    }

    @Test
    public void hasNextTokenFalse() throws Exception {
        r = new StringReader("a//");
        l = new LexerStateMachine(r);
        l.nextToken();
        l.nextToken();
        assertFalse(l.hasNextToken());
    }

}