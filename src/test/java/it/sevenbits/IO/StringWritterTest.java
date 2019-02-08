package it.sevenbits.IO;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class StringWritterTest {
    @Test
    public void writeSome() throws Exception {
        IWritter w = new StringWritter();
        w.write('a');
        w.write('b');
        assertEquals("ab", w.toString());
    }


}