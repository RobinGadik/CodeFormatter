package it.sevenbits.IO;

import java.io.IOException;

public class StringReader implements IReader {

    String in;
    int i=0;

    public StringReader(String in) {
        this.in = in;
        i = 0;
    }

    @Override
    public char read() throws IOException {
        return in.charAt(i++);
    }

    @Override
    public boolean hasNext() {
        return i < in.length();
    }
}
