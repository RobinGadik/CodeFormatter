package it.sevenbits.formaters.sm;

import it.sevenbits.IO.IReader;
import it.sevenbits.IO.IWritter;
import it.sevenbits.IO.StringReader;
import it.sevenbits.IO.StringWritter;
import it.sevenbits.formaters.IFormatter;
import it.sevenbits.lexers.LexerStateMachineFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class StateMachineFormatterTest {
    @Test
    public void formatEmpty() throws Exception {
        IReader r = new StringReader("");
        IFormatter f = new StateMachineFormatter(new LexerStateMachineFactory());
        IWritter w = new StringWritter();
        f.format(r,w);
        assertEquals("", w.toString());
    }
    @Test
    public void formatBaseOpenClose() throws Exception {
        IReader r = new StringReader("{{{{}}}}");
        IFormatter f = new StateMachineFormatter(new LexerStateMachineFactory());
        IWritter w = new StringWritter();
        f.format(r,w);
        assertEquals("{\n    {\n        {\n            {\n            }\n        }\n    }\n}", w.toString());
    }

    @Test
    public void formatBaseComments() throws Exception {
        IReader r = new StringReader("{//asd\n/***/}");
        IFormatter f = new StateMachineFormatter(new LexerStateMachineFactory());
        IWritter w = new StringWritter();
        f.format(r,w);
        assertEquals("{\n    //asd\n    /***/\n}", w.toString());
    }



}