package it.sevenbits;

import static org.junit.jupiter.api.Assertions.*;

class FormatterTest {

    @org.junit.jupiter.api.Test
    void format() {
        Formatter f = new Formatter();

        assertEquals ("WORK", f.format(""));
    }
}