package it.sevenbits;

import it.sevenbits.formaters.SimpleFormatter;

import static org.junit.jupiter.api.Assertions.*;

class FormatterTest {

    @org.junit.jupiter.api.Test
    void test1format() {

        SimpleFormatter f = new SimpleFormatter();
        assertEquals ("aaaa {\n    bbb cb;\n    cccc;\n}\n", f.format("aaaa{bbb cb;cccc;}"));
    }

    @org.junit.jupiter.api.Test
    void test2format() {
        SimpleFormatter f = new SimpleFormatter();
        assertEquals ("aaaa {\n    bbb cb;\n    cccc;\n}\n", f.format("   aaaa {  bbb cb ; cccc ; }"));
    }
}