package it.sevenbits;

import it.sevenbits.IO.*;
import it.sevenbits.lexers.ILexer;
import it.sevenbits.lexers.sm.LexerStateMachine;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Prints "Hello, World" to the terminal window.
        //System.out.println(" CHE MI CHAN GI ");
        FileReader in = new FileReader("pom.xml");
        FileWritter out = new FileWritter("pom1.xml");
        while(in.hasNext()) {
            out.write(in.read());
        }

        IReader e = new StringReader("12345667890абсвгдеЁabcdefg");
        IWritter r = new StringWritter();

        while(e.hasNext()) {
            r.write(e.read());
        }
        System.out.println(r.toString());

        IReader someCode = new StringReader("sadf{f{}f}fsdg{}");
        ILexer lex = new LexerStateMachine(someCode);


    }
}
