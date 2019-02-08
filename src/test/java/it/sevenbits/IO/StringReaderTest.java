package it.sevenbits.IO;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class StringReaderTest {
    @Test (expected = IOException.class)
    public void readIOException() throws Exception {
        IReader r = new StringReader("");
        r.read();
    }
    @Test
    public void read() throws Exception {
        IReader r = new StringReader("asd");
        r.read();
    }

    @Test
    public void hasNextTrue() throws Exception {
        IReader r = new StringReader("asd");
        assertTrue(r.hasNext());

    }
    @Test
    public void hasNextFalse() throws Exception {
        IReader r = new StringReader("asd");
        r.read();
        r.read();
        r.read();
        assertFalse(r.hasNext());
    }

}