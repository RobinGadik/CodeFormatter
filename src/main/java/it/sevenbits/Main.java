package it.sevenbits;

import it.sevenbits.IO.*;
import it.sevenbits.formaters.IFormatter;
import it.sevenbits.formaters.sm.StateMachineFormatter;
import it.sevenbits.lexers.ILexer;
import it.sevenbits.lexers.LexerException;
import it.sevenbits.lexers.LexerStateMachineFactory;
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

        IReader someCode = new StringReader(" sad  f\" dfgg \";{f{} / /s;;  f }fs \'\n\' d g{} ");
        ILexer lex = new LexerStateMachine(someCode);

        IReader someCode1 = new StringReader(" {//sdfgdfh2354\n} /*sd\n*\n***/");
        ILexer lex1 = new LexerStateMachine(someCode1);

        IFormatter formatter = new StateMachineFormatter(new LexerStateMachineFactory());
        r = new StringWritter();
        someCode = new StringReader(" sad  f\" dfgg \";{f{} //s{}}};;  f }fs \'\n\' d g{}}");

        try {
            formatter.format(someCode, r);
        } catch (LexerException e1) {
            e1.printStackTrace();
        }

        System.out.println(r.toString());
        r = new StringWritter();
        someCode1 = new StringReader("{{{{}}}}");

        try {
            formatter.format(someCode1, r);
        } catch (LexerException e1) {
            e1.printStackTrace();
        }

        System.out.println(r.toString());
    }
}
