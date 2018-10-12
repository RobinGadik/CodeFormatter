package it.sevenbits;

import static org.junit.jupiter.api.Assertions.*;

class FormatterTest {

    @org.junit.jupiter.api.Test
    void test1format() {
        Formatter f = new Formatter();
        assertEquals ("aaaa {\n    bbb cb;\n    cccc;\n}\n", f.format("aaaa{bbb cb;cccc;}"));
    }

    @org.junit.jupiter.api.Test
    void test2format() {
        Formatter f = new Formatter();
        assertEquals ("aaaa {\n    bbb cb;\n    cccc;\n}\n", f.format("   aaaa {  bbb cb ; cccc ; }"));
    }
}