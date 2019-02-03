package it.sevenbits.IO;

import java.io.IOException;

public class StringWritter implements IWritter {

    StringBuilder out;

    public StringWritter() {
        this.out = new StringBuilder();
    }

    public StringWritter(StringBuilder out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return out.toString();
    }

    @Override
    public void write(char c) throws IOException {
        out.append(c);
    }
}
