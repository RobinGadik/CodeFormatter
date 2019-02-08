package it.sevenbits.IO;

import java.io.IOException;

/**
 * Parse String to chars
 */
public class StringReader implements IReader {

    private String in;
    private int i = 0;

    /**
     * @param in String to parse
     */
    public StringReader(final String in) {
        this.in = in;
        i = 0;
    }

    @Override
    public char read() throws IOException {
        if (i < in.length()) {
            return in.charAt(i++);
        } else {
            throw new IOException("End of StringReader input");
        }

    }

    @Override
    public boolean hasNext() {
        return i < in.length();
    }
}
