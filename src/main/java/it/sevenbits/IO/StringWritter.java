package it.sevenbits.IO;

import java.io.IOException;

/**
 * Why double t? cause of!
 * Use to write to String
 * Use toString for take result
 */
public class StringWritter implements IWritter {

    private StringBuilder out;

    /**
     * default use new StringBuilder
     */
    public StringWritter() {
        this.out = new StringBuilder();
    }

    /**
     * @param out StringBuilder if you want to use own
     */
    public StringWritter(final StringBuilder out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return out.toString();
    }

    @Override
    public void write(final char c) throws IOException {
        out.append(c);
    }
}
